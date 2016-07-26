;$(function(){
	var init = function() {
		$('#fileButton').click(chooseFile);
		$('#fileInput').change(browseFile);
		initRemoveArticleButton();
		initPaginationButtons();
		$('#signoutBtn').click(submitSignoutForm);
		initLoadAllComments();
		$('#loadAllCommentBtn').click(loadAllComments);
		$('#addCommentBtn').click(sendAddComment);
		initDelCommentBtn();
	};

	var chooseFile = function() {
		$('#fileInput').click();
	};
	var browseFile = function() {
		$('#fileName').html($('#fileInput').val().split(/[\\|/]/).pop());
	};
	var initRemoveArticleButton = function() {
		$('.removeArticle').click(removeArticle);
	};
	var removeArticle = function() {
		var btn = $(this);
		var idArticle = btn.attr('data-id-article');
		$('#article'+idArticle).remove();
	};
	var initPaginationButtons = function() {
		var number = parseInt($('.pagination').attr('data-page-number'));
		var total = parseInt($('.pagination').attr('data-page-total'));
		var categoryUrl = $('.pagination').attr('data-category-url');
		if (number == 0) {
			$('#prev').addClass('disabled');
		}
		else {
			$('#prev .pgLink').attr('href', categoryUrl + '?page=' + (number - 1));
		}
		if (number == total - 1) {
			$('#next').addClass('disabled');
		}
		else {
			$('#next .pgLink').attr('href', categoryUrl + '?page=' + (number + 1));
		}
		var first, last, diff;
		if (total > 5) {
			first = number - 2;
			last = number + 2;
			diff = 0;
			if (first < 0) {
				diff = -first;
			}
			if (last > total - 1) {
				diff = total - 1 - last;
			}
			first += diff;
			last += diff;
		} else {
			first = 0;
			last = total - 1;
		}
		for (var n = 1, pg = first; n <= 5; n++, pg++) {
			if (pg <= last) {
				$('#btn' + n + ' .pgLink').text(pg + 1);
				if (pg == number) {
					$('#btn' + n).addClass('active');
				} else {
					$('#btn' + n + ' .pgLink').attr('href', categoryUrl + '?page=' + pg);
				}
			}
			else {
				$('#btn' + n).addClass('hidden');
			}
		}
	};
	var submitSignoutForm = function() {
		$('#signoutForm').submit();
	};
	var initLoadAllComments = function() {
		$('#loadAllCommentIndicator').css('display', 'none');
		var number = parseInt($('#commentContainer').attr('data-first-comments'));
		var total = parseInt($('#commentContainer').attr('data-total-comments'));
		if (number >= total) {
			$('#comment-more').remove();
		};
	};
	var initDelCommentBtn = function() {
		var btns = $('.delCommentBtn');
		btns.off('click', sendDelComment);
		btns.on('click', sendDelComment);
	};
	var loadAllComments = function() {
		var articleId = parseInt($('#commentContainer').attr('data-article-id'));
		var url = '/comment/all?articleId=' + articleId;
		
		$('#loadAllCommentBtn').css('display', 'none');
		$('#loadAllCommentIndicator').css('display', 'block');
		$.ajax({
			url : url,
			success : function(data) {
				$('#commentContainer').html(data);
				$('#comment-more').remove();
				initDelCommentBtn();
			},
			error : function(data) {
				$('#loadAllCommentIndicator').css('display', 'none');
				$('#loadAllCommentBtn').css('display', 'block');
				errorAlert();
			}
		});
	};
	var sendAddComment = function() {
		var url = "/user/comment/add";
		var csrfToken = $('#csrfToken').val();
		var articleId = $('#articleId').val();
		var authorId = $('#authorId').val();
		var text = $('#addCommentText').val();
		var data = {
			articleId: articleId,
			authorId: authorId,
			text: text
		};
		if (text !== '') {
			$.ajaxSetup({
		        headers: {
		            'X-Csrf-Token': csrfToken
		        }
		    });
			$.ajax({
				url : url,
				type: 'POST',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success : function(data) {
					$('#commentContainer').prepend(data);
					$('#addCommentText').val('');
					initDelCommentBtn();
				},
				error : function(data) {
					errorAlert();
				}
			});
		};
	};
	var sendDelComment = function() {
		var btn = $(this);
		var url = "/user/comment/del";
		var csrfToken = $('#csrfToken').val();
		var commentId = btn.attr('data-comment-id');
		$.ajaxSetup({
	        headers: {
	            'X-Csrf-Token': csrfToken
	        }
	    });
		$.ajax({
			url : url,
			type: 'POST',
			data: {
				commentId: commentId
			},
			success : function(data) {
				if (data.status === 'OK') {
					$('#comment' + commentId).remove();
				}
			},
			error : function(data) {
				errorAlert();
			}
		});
	};
	var errorAlert = function() {
		alert('Error! Try again later...');
	};

	init();
});
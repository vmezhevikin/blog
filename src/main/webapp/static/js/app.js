;$(function(){
	var init = function() {
		$('#fileButton').click(chooseFile);
		$('#fileInput').change(browseFile);
		initRemoveArticleButton();
		initPaginationButtons();
		$('#signoutBtn').click(submitSignoutForm);
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

	init();
});

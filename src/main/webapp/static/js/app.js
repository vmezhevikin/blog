;$(function(){
	var init = function() {
		$('#fileButton').click(chooseFile);
		$('#fileInput').change(browseFile);
		initRemoveArticleButton();
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

	init();
});

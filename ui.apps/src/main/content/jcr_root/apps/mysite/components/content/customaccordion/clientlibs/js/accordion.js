$(document).ready(function() {
    $('.accordion-btn').click(function() {

      if( !$(this).hasClass('active')){
		$(this).addClass('active');
        $(this).next('.panel').show();  
      }else{
		$(this).removeClass('active');
        $(this).next('.panel').hide();
      }  
    });
  });
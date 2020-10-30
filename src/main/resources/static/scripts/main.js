jQuery('document').ready(function(){

    // -------------------------------------------- //
    //menu expand/collapse ------------------------ //
    // -------------------------------------------- //    
    const menu = jQuery(".navbar-toggler"),
          expand = jQuery("#navbarNav");

    //menu on click
    menu.on("click", function(e){          
        
        //prevent default
        e.preventDefault();
        
        //switch out
        expand.hasClass("collapse") ? expand.removeClass("collapse") : expand.addClass("collapse");
        
    });
    
    // -------------------------------------------- //
    //close button on alerts ---------------------- //
    // -------------------------------------------- //
    //init selectors
    const close = jQuery(".close");

    //close parent on click
    close.on("click", function(e){

        //prevent default
        e.preventDefault();

        //remove from DOM
        jQuery(this).parent().remove();

    });

});
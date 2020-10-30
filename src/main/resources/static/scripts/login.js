jQuery('document').ready(function(){

	//init urls
	const url = window.location.href.split("/"), len = url.length; 
	
	//if we are on the regirstion process keep that tab active
	if((url[len - 2] + "/" + url[len - 1]) === "register/process"){
	
		//make reg tab active
		jQuery("a[data-target='registration-form']").addClass("active");
		jQuery("a[data-target='login-form']").removeClass("active");
		
		//hide login and show registration
		jQuery(".login-form").removeClass("show").addClass("hide");
		jQuery(".registration-form").removeClass("hide").addClass("show"); 
		
	}

	//tab swap
	jQuery(".login-wrapper").find(".nav-item a").on("click", function(e){
		
		//prevent default click
		e.preventDefault();

		if(jQuery(this).hasClass("active")){
			return;
		}

		//toggle class for blur spin effect
		jQuery(".login-wrapper").addClass("spin");
		setTimeout(function (){
			jQuery(".login-wrapper").removeClass("spin");
		},600);

		//get this item
		let thisItem = jQuery(this);
		
		//remove active class and hide all forms
		jQuery(".login-wrapper").find(".nav-link").removeClass("active");
		jQuery(".login-wrapper").find(".forms").removeClass("show").addClass("hide");
		
		//get target attrib data
		let currentForm = thisItem.data("target");
		
		//add active to clicked tab and show current form
		thisItem.addClass("active");
		jQuery("." + currentForm).removeClass("hide").addClass("show"); 
		
	});
	
});
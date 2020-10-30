jQuery('document').ready(function(){
    
    // -------------------------------------------- //
    //WYSIWYG ------------------------------------- //
    // -------------------------------------------- //
	jQuery('#ingredients, #directions').summernote({
        spellCheck:true,
        height: 120,
        toolbar:[
          ['font', ['bold', 'italic', 'underline']],
          ['para', ['ul', 'ol']],
          ['view', ['fullscreen', 'codeview']]
        ]
	  }).on("summernote.paste",function(e,ne){
          var bufferText = ((ne.originalEvent || ne).clipboardData || window.clipboardData).getData('Text');
          ne.preventDefault();
          document.execCommand('insertText', false, bufferText);
        });


    // -------------------------------------------- //
    //file input display file name ---------------- //
    // -------------------------------------------- //
	jQuery('#image').on('change', function(){
	    
	    let fileName = jQuery(this).val().replace('/', "\\").split("\\");
	    
	    //update file name
	    jQuery(this).next('.custom-file-label').html(fileName[fileName.length - 1]);
		
	})

});
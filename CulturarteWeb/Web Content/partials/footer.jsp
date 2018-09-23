<footer>
</footer>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resources/js/popper.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" charset="utf-8"></script>
<script>
   /* inicializo tooltips */
   $(function () {
     $('[data-toggle="tooltip"]').tooltip();
   });
   
</script>
<script>
   $(document).ready(function() {
   	   $('input[type="radio"]').click(function() {
   	       if($(this).attr('value') == 'proponente') {
   	            $('#form-proponente').show();           
   	       }
   
   	       else {
   	            $('#form-proponente').hide();   
   	       }
   	   });
   	});
</script>

<script>
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#imagenUsuario')
                .attr('src', e.target.result)
                .width(200)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
</script>

</body>
</html>
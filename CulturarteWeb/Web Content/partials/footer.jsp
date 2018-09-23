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
   	       if($(this).attr('id') == 'proponente') {
   	            $('#form-proponente').show();           
   	       }
   
   	       else {
   	            $('#form-proponente').hide();   
   	       }
   	   });
   	});
     
</script>
</body>
</html>
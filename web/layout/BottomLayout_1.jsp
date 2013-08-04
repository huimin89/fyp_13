<!--
 * Purpose: 2nd half template for the all the remaining page. Reusable for all other pages  
 * Date:14 July 2013
 * Author: Huimin
 * 
 -->
        </div>
    </div>

    <!-- sidebar -->
    <a href="javascript:void(0)" class="sidebar_switch on_switch ttip_r" title="Hide Sidebar">Sidebar switch</a>
    <div class="sidebar">
        <div class="antiScroll">
            <div class="antiscroll-inner">
                <div class="antiscroll-content">
                    <div class="sidebar_inner">
                        <form action="" class="input-append" method="post" >
                                <input autocomplete="off" name="query" class="search_query input-medium" size="16" type="text" placeholder="Search..." /><button type="submit" class="btn"><i class="icon-search"></i></button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
            
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery.pajinate.js"></script>
    <!-- smart resize event -->
    <script src="../js/jquery.debouncedresize.min.js"></script>
    <!-- hidden elements width/height -->
    <script src="../js/jquery.actual.min.js"></script>
    <!-- js cookie plugin -->
    <script src="../js/jquery.cookie.min.js"></script>
    <!-- main bootstrap js -->
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <!-- tooltips -->
    <script src="../lib/qtip2/jquery.qtip.min.js"></script>
    <!-- fix for ios orientation change -->
    <script src="../js/ios-orientationchange-fix.js"></script>
    <!-- scrollbar -->
    <script src="../lib/antiscroll/antiscroll.js"></script>
    <script src="../lib/antiscroll/jquery-mousewheel.js"></script>
    <!-- common functions -->
    <script src="../js/gebo_common.js"></script>
    <!----BarChart-->
    <script src="../js/jqBarGraph.1.1.min.js"></script>

    <script>
            $(document).ready(function() {
                    //* show all elements & remove preloader
                    setTimeout('$("html").removeClass("js")',1000);
            });
    </script>
		
        </div>
    </body>
</html>
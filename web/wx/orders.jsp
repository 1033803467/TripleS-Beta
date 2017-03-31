<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String openid = request.getAttribute("openid").toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/jquery.mobile.flatui.css" />
    <script src="js/jquery.js"></script>
 	<!--<script src="js/jquery.mobile-1.4.0-rc.1.js"></script>-->
    <style type="text/css">
        .pic{
            float: left;
            margin:20px;
            padding:0;
        }
        .desc{
            float: left;
            margin-left: 20px;
        }
        .js-load-more{
            display:none;/*默认不显示，ajax调用成功后才决定显示与否*/
        }
    </style>
</head>
<body>
<div data-role="page" id="pageone">
    <div data-role="header" data-theme="b" role="banner">
        <h1>消费记录查询</h1>
    </div>
    <div data-role="main" class="ui-content">
        <a data-theme="a" style="display:block;width:100%;border-radius:10px;" data-role="button" data-ajax="true" href="javascript:;" class="ui-btn ui-btn-a ui-btn-corner-all js-load-more">点击加载更多</a>
    </div>
    <div data-role="collapsible" data-theme="b" id="collapsible" style="display:none">   	       	
        	<h6>
	        	<a href="#" >
	        		文本
	        		<span class="ui-collapsible-heading-status"></span>
	        	</a>
        	</h6>       	
        	<div aria-hidden="false">      		        	
					<ul data-role="listview" data-inset="true" data-theme="c" id="listview" >
						<li data-icon="false" class="ui-li-has-thumb ui-first-child ui-last-child"> 
							<a class="ui-btn"> 
								<img class="pic" src=""/> 
								<div class="desc" > 
									<p>'+data[i].detailList[j].gName+'</p>
									<p>'+data[i].detailList[j].gSpecification+'/'+data[i].detailList[j].price+'</p> 
									<p>'+data[i].detailList[j].quantity+'</p> 
									<p>'+data[i].detailList[j].itemsAmount+'</p>
								 </div>
							 </a> 
							</li>
					</ul>
			</div>
		</div>
  <script>
    /*
    *你自己封装一个外部文件出去
    *
    */
    	var array=new Array();
    	//var fstTile="ui-collapsible ui-collapsible-inset ui-corner-all ui-collapsible-themed-content ui-collapsible-collapsed";
    	//var sndTile="ui-collapsible-heading ui-collapsible-heading-collapsed";
    	//var sonTitle="ui-collapsible-heading-toggle ui-btn ui-btn-icon-left ui-btn-b ui-icon-plus";
    	//var trdTitle="ui-collapsible-content ui-body-inherit ui-collapsible-content-collapsed";
    	//var fthTitle="ui-listview ui-listview-inset ui-corner-all ui-shadow ui-group-theme-c";
    	var fstTile="ui-collapsible ui-collapsible-inset ui-corner-all ui-collapsible-themed-content ";
    	var sndTile="ui-collapsible-heading ";
    	var sonTitle="ui-collapsible-heading-toggle ui-btn ui-btn-icon-left ui-btn-b ui-icon-minus ";
    	var trdTitle="ui-collapsible-content ui-body-inherit ";
    	var fthTitle="ui-listview ui-listview-inset ui-corner-all ui-shadow ui-group-theme-c ";
    	var obj={"fstTile":fstTile,"sndTile":sndTile,"trdTitle":trdTitle,"fthTitle":fthTitle,"sonTitle":sonTitle};
    	//var fstRemove="ui-collapsible-collapsed";
    	//var sndRemove="ui-collapsible-heading-collapsed";
    	//var trdRemove="ui-collapsible-content-collapsed";
    	//var sonRemove="ui-icon-plus";
		//var status=false;
    	var fstAdd="ui-collapsible-collapsed";
    	var sndAdd="ui-collapsible-heading-collapsed";
    	var trdAdd="ui-collapsible-content-collapsed";
    	var sonAdd="ui-icon-plus";
    	var status=true;
    	var objRemove={"fstRemove":fstAdd,"sndRemove":sndAdd,"trdRemove":trdAdd,"sonRemove":sonAdd,"status":status};
    	var objCss={"body":"ui-mobile-viewport ui-overlay-a","pageOne":"ui-page ui-page-theme-a ui-page-active"};
    function cs(){
    	$("body").attr("class",objCss.body);
    	$("#pageone").css("min-height","50%").attr({"data-url":"pageone","tabindex":"0"}).attr("class",objCss.body);
    	$("#pageone div").eq(0).attr("class","ui-header ui-bar-b");
    	$("div h1").attr({"class":"ui-title","role":"heading","aria-level":"1"});
    }
    function init(node,array){
    	var flag=true;
   	    addClas(node);
   	    node.click(
   	   		function(){
   	   			if(flag){
   	   				flag=false;
   	   				//removeClas(node);//"#collapsible"
   	   				addClas(node);
   	   			}else{
   	   				flag=true;
   	   				//addClas(node);
   	   				removeClas(node);
   	   			}  	   			
   	   		}
   	    );//.click(function(){distinct(node,array);});   
    }
    //展开
    function addClas(node){
    	node.attr("class",obj.fstTile);
    	node.find("h6").attr("class",obj.sndTile);
    	node.children("div").attr("class",obj.trdTitle); 	
    	node.find("ul").attr("class",obj.fthTitle);
    	node.find("a").eq(0).attr("class",obj.sonTitle);
    	
    }
    //收起，不能使用replace方法
    function removeClas(node){
    	node.attr("class",obj.fstTile+objRemove.fstRemove);//.replace(objRemove.fstRemove,""));
    	node.find("h6").attr("class",obj.sndTile+objRemove.sndRemove);//.replace(objRemove.sndRemove,""));
    	node.children("div").attr("class",obj.trdTitle+objRemove.trdRemove).attr("aria-hidden",objRemove.status);//.replace(objRemove.trdRemove,"")).attr("aria-hidden",objRemove.status);
    	//node.find("a").eq(0).attr("class",obj.sonTitle.replace("ui-icon-plus",objRemove.sonRemove));
    	node.find("a").eq(0).attr("class",obj.sonTitle.replace("ui-icon-minus",objRemove.sonRemove));
    }
    function prepare(node,index,object){
    	if(object.gName==null){
    		node.children("h6").children("a").text(object.oId+' '+object.oDate+' ￥'+object.amount);
    	}else{//如果是第二次调用直接将index传成路径
    		node.find("img").attr("src",index);
    		node.find("p").eq(0).text(object.gName);
    		node.find("p").eq(1).text(object.gSpecification+'/'+object.price);
    		node.find("p").eq(2).text(object.quantity);
    		node.find("p").eq(3).text(object.itemsAmount);
    	}    								   	
    }
    function distinct(node,array){
   		//alert(array[0].children("div").attr("aria-hidden"));
    	for(var i=0;i<array.length;i++){
    		if(array[i].children("div").attr("aria-hidden")=="true" && node.attr("id")!=array[i].attr("id")){   			
    			addClas(array[i]);
    		}
    		//if(array[i].children("div").attr("aria-hidden")=="true" && node.attr("id")!=array[i].attr("id")){   			
    			//addClas(array[i]);
    		//}
    	}
    }
    $(function(){   	
    	cs();  		
        /*初始化*/
        var counter = 0; /*计数器*/
        var pageStart = 0; /*offset*/
        var pageSize = 5; /*size*/

        /*首次加载*/
        getData(pageStart, pageSize);

        /*监听加载更多*/
         $(document).on('click', '.js-load-more', function(){
            counter ++;
            pageStart = counter * pageSize;
            getData(pageStart, pageSize);
        });  
    });

    function getData(offset,size){    	
    	 //var sum = 2;
    	 //var object={"gName":"lili","price":25};
         $.ajax({
            type: 'GET',
            url: '../PageServlet?type=4&openid=<%=openid%>',
            dataType: 'json',
            success: function(response){ 
                var data = response;
				var sum = response.length;
                //var result = ''; 
                /****业务逻辑块：实现拼接html内容并append到页面*********/

                //console.log(offset , size, sum);

                /*如果剩下的记录数不够分页，就让分页数取剩下的记录数
                 * 例如分页数是5，只剩2条，则只取2条
                 * 实际MySQL查询时不写这个不会有问题
                 */
                 if(sum - offset < size ){
                    size = sum - offset;
                } 
                /*使用for循环模拟SQL里的limit(offset,size)*/
                //我把这里的i的取值改了，你自己重新设置最大值
                for(var i=offset; i<(offset+size); i++){//(offset+size)
                	//目前的情况是数据均为获取到因此该用字符串"visibility","visible"
                	var node=$("#collapsible").clone().css("display","block").attr("id","collapsible"+i);
                	//此时的object应该传data[i]
						prepare(node,i,data[i]);	
                		//prepare(node,i,object);													
					for(var j=0;j<1;j++){
						//此时的object应该传data[i][j],path处必须传图片的路径，如果传完路径必须去prepare方法中打开注释的语句
						prepare(node,data[i].detailList[j].gPic,data[i].detailList[j]);
					}
					 array.push(node);
                	 init(node,array);
                	 $('.ui-content').prepend(node);
                }       
                /*******************************************/
                /*隐藏more按钮*/
                 if ( (offset + size) >= sum){
                    $(".js-load-more").hide();
                }else{
                    $(".js-load-more").show();
                }
            },
            error: function(xhr, type){
                alert('Ajax error!');
            } 
        });
    }
    
</script>
</div>
</body>
</html>

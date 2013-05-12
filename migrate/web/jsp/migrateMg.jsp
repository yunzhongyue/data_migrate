<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据迁移控制台</title>
   
  	<link rel="stylesheet" href="../dhtmlx/dhtmlx.css" type="text/css"></link>
  	<link rel="stylesheet" href="../dhtmlx/types/ftypes.css" type="text/css"></link>
  	<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
  	<script type="text/javascript" src="../dhtmlx/dhtmlx.js"></script>
  	<script type="text/javascript" src="../dhtmlx/types/ftypes.js"></script>
  	<script type="text/javascript" src="../dhtmlx/connector.js"></script>
  	
  	<style type="text/css">
  		
		html, body {
			width: 100%;
			height: 100%;
			margin: 0px;
			padding: 0px;
			overflow: hidden;
		}
  	</style>
  	
  	<script type="text/javascript">
		
  		dhtmlx.image_path='../dhtmlx/imgs/';
  		
  		function randomChar(length)  { 
  			 
  			length = length || 32;
  			var source = "ABCDEF1234567890";
  			  //生成的随机数从该字符串中进行动态截取字符
  			  var random = "";
  			  for(var i = 0;i < length; i++)  {
  			    random += source.charAt(Math.ceil(Math.random()*100000000)%source.length);
  			    }
  			  return random;
  			}
		
  		window.onload=function(){
		
		var main_layout = new dhtmlXLayoutObject(document.body, '1C');
	
		var a = main_layout.cells('a');
		var tabbar = a.attachTabbar();
		tabbar.addTab('planManager','计划管理','');
		var planManager = tabbar.cells('planManager');
		tabbar.setTabActive('planManager');
		var plans = planManager.attachGrid();
		plans.setIconsPath('../codebase/imgs/');
		
		plans.setHeader(["名称","创建人","源连接方式","目标连接方式","状态","已完成任务数"]);
		plans.setColTypes("ro,ro,ro,ro,ro,ro");
		plans.setColSorting('str,str,str,str,str,str');
		
		plans_cxtMenu = new dhtmlXMenuObject();
		plans_cxtMenu.renderAsContextMenu();

		plans_cxtMenu.loadXMLString('<menu><item type="item" id="detail" text="查看" img="" /><item type="item" id="edit" text="编辑" img="" /><item type="item" id="delete" text="删除" img="" /><item type="separator" id="menuitem_separator" /><item type="item" id="lookTask" text="查看任务" img="" /></menu>');
		plans.enableContextMenu(plans_cxtMenu);
		plans_cxtMenu.attachEvent('onClick', function(id){
			if(id=="detail")
			{
				
			}
			else if(id=="edit")
			{
				
			}
			else if(id=="delete")
			{
				deletePlans();
			}
			else if(id=="lookTask")
			{
				showTasks();
			}
		});
		
		plans.init();
		plans.load('/migrate/manager/planManager.action', 'xml');
		
		var toolbar = planManager.attachToolbar();
		toolbar.setIconsPath('./codebase/imgs/');
		
		toolbar.loadXML('../data/plans_toolbar.xml', function(){});
	
	
		tabbar.addTab('monitor','监视器','');
		var monitor = tabbar.cells('monitor');
	
	
		tabbar.addTab('setting','系统设置','');
		var setting = tabbar.cells('setting');
	
	
	
		toolbar.attachEvent('onClick', function(id){
			if(id=="new")
			{
				newPlan();
			}
			else if(id=="new_db_db")
			{
				createDB_to_DB();
			}
			else if(id=="new_db_ftp")
			{
				
			}
			else if(id=="new_db_ws")
			{
				
			}
			else if(id=="new_ftp_db")
			{
				
			}
			else if(id=="new_ftp_ftp")
			{
				
			}
			else if(id=="new_ftp_ws")
			{
				
			}
			else if(id=="new_ws_db")
			{
				
			}
			else if(id=="new_ws_ftp")
			{
				
			}
			else if(id=="new_ws_ws")
			{
				
			}
			else if(id=="new_other")
			{
				
			}
			else if(id=="detail")
			{
				
			}
			else if(id=="edit")
			{
				
			}
			else if(id=="delete")
			{
				deletePlans();
			}
			else if(id=="lookTask")
			{
				showTasks();
			}
		});
	
	
	
	
		var windows = new dhtmlXWindows();
		function newPlan()
		{
			var new_plan=windows.createWindow('new_plan',0,0,350,580);
			var new_plan_form_str = [
			   		{ type:"settings" , labelWidth:80, inputWidth:250, position:"absolute"  },
			   		{ type:"input" , name:"planName", label:"计划名称：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:25, inputLeft:25, inputTop:46  },
			   		{ type:"select" , name:"src_connType", label:"源连接类型：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:75, inputLeft:25, inputTop:96 },
			   		{ type:"input" , name:"src_connStr", label:"源连接字符串：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:125, inputLeft:25, inputTop:146  },
			   		{ type:"input" , name:"src_username", label:"源连接用户名：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:175, inputLeft:25, inputTop:196  },
			   		{ type:"input" , name:"src_password", label:"源连接密码：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:225, inputLeft:25, inputTop:246  },
			   		{ type:"select" , name:"target_connType", label:"目标连接类型：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:275, inputLeft:25, inputTop:296 },
			   		{ type:"input" , name:"target_connStr", label:"目标连接字符串：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:325, inputLeft:25, inputTop:346  },
			   		{ type:"input" , name:"target_username", label:"目标连接用户名：", labelWidth:250, labelLeft:25,labelAlign:"left",required:true, labelTop:375, inputLeft:25, inputTop:396  },
			   		{ type:"input" , name:"target_password", label:"目标连接密码：", labelWidth:250, labelAlign:"left", required:true, labelLeft:25, labelTop:425, inputLeft:25, inputTop:446  },
			   		{ type:"button" , name:"submit", label:"提交", value:"提交", width:"250", inputLeft:25, inputTop:490  }
			   	];
			var new_plan_form=new_plan.attachForm(new_plan_form_str);
			new_plan_form.attachEvent("onButtonClick",function(name){
				if(name=="submit")
				{
					
				}
			});
			
			new_plan.setText("新建计划");
			new_plan.centerOnScreen();
			new_plan.denyResize();
			new_plan.button('park').hide();
			new_plan.button('minmax1').hide();
		}
		function createDB_to_DB()
		{
			var db_db = windows.createWindow('db_db', 0, 0, 350, 580);
			var db_db_form_str=[
			            		{ type:"settings" , labelWidth:80, inputWidth:250, position:"absolute"  },
			            		{ type:"input" , name:"planName", label:"计划名称：", labelWidth:250, labelAlign:"left", required:true,labelLeft:25, labelTop:25, inputLeft:25, inputTop:46  },
			            		{ type:"select" , name:"src_DBType", label:"源数据库类型：",options:[{text:"oracle",value:"oracle",selected:true},{text:"mysql",value:"mysql"},{text:"sqlServer",value:"sqlServer"}],labelWidth:250, labelAlign:"left", required:true,labelLeft:25, labelTop:75, inputLeft:25, inputTop:96 },
			            		{ type:"input" , name:"src_DBIP", label:"源数据库地址：", labelWidth:250, labelAlign:"left", required:true,labelLeft:25, labelTop:125, inputLeft:25, inputTop:146  },
			            		{ type:"input" , name:"src_DBPort", label:"源数据库端口号：", validate:"ValidInteger",labelWidth:250, labelAlign:"left", required:true,labelLeft:25, labelTop:175, inputLeft:25, inputTop:196  },
			            		{ type:"input" , name:"src_DBName", label:"源数据库名称：", labelWidth:250, labelAlign:"left", required:true,labelLeft:25, labelTop:225, inputLeft:25, inputTop:246  },
			            		{ type:"input" , name:"src_username", label:"源数据库用户名：", labelWidth:250, labelAlign:"left", required:true, labelLeft:25, labelTop:275, inputLeft:25, inputTop:296  },
			            		{ type:"input" , name:"src_password", label:"源数据库密码：", labelWidth:250, labelAlign:"left", required:true, labelLeft:25, labelTop:325, inputLeft:25, inputTop:346  },
			            		{ type:"select" , name:"target_DBType", label:"目标数据库类型：",options:[{text:"oracle",value:"oracle",selected:true},{text:"mysql",value:"mysql"},{text:"sqlServer",value:"sqlServer"}],labelWidth:250, labelAlign:"left",required:true, labelLeft:25, labelTop:375, inputLeft:25, inputTop:396 },
			            		{ type:"input" , name:"target_DBIP", label:"目标数据库地址：", labelWidth:250, labelAlign:"left",required:true, labelLeft:25, labelTop:425, inputLeft:25, inputTop:446  },
			            		{ type:"input" , name:"target_DBPort", label:"目标数据库端口号：", validate:"ValidInteger",labelWidth:250, labelAlign:"left",required:true, labelLeft:25, labelTop:475, inputLeft:25, inputTop:496  },
			            		{ type:"input" , name:"target_DBName", label:"目标数据库名称：", labelWidth:250, labelAlign:"left", required:true, labelLeft:25, labelTop:525, inputLeft:25, inputTop:546  },
			            		{ type:"input" , name:"target_username", label:"目标数据库用户名：", labelWidth:250, labelAlign:"left", required:true, labelLeft:25, labelTop:575, inputLeft:25, inputTop:596  },
			            		{ type:"input" , name:"target_password", label:"目标数据库密码：", labelWidth:250, labelAlign:"left", required:true, labelLeft:25, labelTop:625, inputLeft:25, inputTop:646  },
			            		{ type:"button" , name:"submit", label:"提交", value:"提交", width:"250", inputLeft:25, inputTop:680  }
			            	];
			var db_db_form = db_db.attachForm(db_db_form_str);
			db_db_form.enableLiveValidation(true);
			db_db_form.attachEvent("onButtonClick",function(name){
				if(name=="submit")
				{
					var values=db_db_form.getFormData();
					var planId,planName,src_DBType,src_DBIP,src_DBPort,src_DBName,src_username,src_password,target_DBType,target_DBIP,target_DBPort,target_DBName,target_username,target_password;
					planId=randomChar(32);
					planName=values.planName;
					src_DBType=values.src_DBType;
					src_DBIP=values.src_DBIP;
					src_DBPort=values.src_DBPort;
					src_DBName=values.src_DBName;
					src_username=values.src_username;
					src_password=values.src_password;
					target_DBType=values.target_DBType;
					target_DBIP=values.target_DBIP;
					target_DBPort=values.target_DBPort;
					target_DBName=values.target_DBName;
					target_username=values.target_username;
					target_password=values.target_password;
					var params="method=add&addType=db_db&planId="+planId+"&planName="+planName+"&src_DBType="+src_DBType+"&src_DBIP="+src_DBIP+"&src_DBPort="+src_DBPort+"&src_DBName="+src_DBName+"&src_username="+src_username+"&src_password="+src_password+
					"&target_DBType="+target_DBType+"&target_DBIP="+target_DBIP+"&target_DBPort="+target_DBPort+"&target_DBName="+target_DBName+"&target_username="+target_username+"&target_password="+target_password
					dhtmlxAjax.post("/migrate/manager/planManager.action",params,function(loader){
						
						var result=loader.xmlDoc.responseText;
						if(result==1)
						{
							plans.addRow(planId,[planName,"12",src_DBType,target_DBType,"未启动","0"]);
							db_db.close();
						}
					});
				}
			});

			db_db.setText('DB-->DB');
			db_db.centerOnScreen();
			db_db.denyResize();
			db_db.button('park').hide();
			db_db.button('minmax1').hide();
		}
		
		function deletePlans()
		{
			var id=plans.getSelectedRowId();
			if(id==null)
			{
				dhtmlx.message("未选择任何计划，无法执行删除！");
			}
			else
			{
				dhtmlxAjax.post("/migrate/manager/planManager.action","method=delete&planId="+id,function(loader){
					
					var result=loader.xmlDoc.responseText;
					if(result>=1)
					{
						plans.deleteRow(id);
					}
				});
			}
		}
		function showTasks()
		{
			var plan_id=plans.getSelectedRowId();
			if(plan_id==null)
			{
				dhtmlx.message("请先选择一个计划!");
			}
			else
			{
				var tasks = windows.createWindow('tasks', 0, 0, 1000, 600);
				var task_toolbar = tasks.attachToolbar();
				task_toolbar.loadXML('../data/task_toolbar.xml');
				task_toolbar.attachEvent('onClick', function(id){
					if(id=="new")
					{
						addTask(plan_id);
					}
					else if(id=="detail")
					{
						
					}
					else if(id=="edit")
					{
						
					}
					else if(id="delete")
					{
						
					}
				});
				
				var task_grid = tasks.attachGrid();
				
				task_grid.setHeader(["任务名称","状态","下次运行时间","上次运行时间","上次运行结果","创建者","创建时间"]);
				task_grid.setColTypes("ro,ro,ro,ro,ro,ro,ro");
				
				task_grid.setColSorting('str,str,str,str,str,str,str');
				task_grid.init();
				task_grid.load('../data/grid.xml', 'xml');
				
				
				tasks.setText('任务');
				tasks.centerOnScreen();
			}
		}
		
		function addTask(planId)
		{
			var new_task=windows.createWindow('new_plan',0,0,350,580);
			
			var task_form_str = [
			   		{ type:"settings" , labelWidth:80, inputWidth:250, position:"absolute"  },
			   		{ type:"input" , name:"taskName", label:"任务名称：", validate:"ValidInteger", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:0, inputLeft:25, inputTop:20  },
			   		{ type:"select" , name:"parentTask", label:"前置任务：", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:50, inputLeft:25, inputTop:70 },
			   		{ type:"combo" , name:"cycleType", label:"周期类型：", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:100, inputLeft:25, inputTop:120  },
			   		{ type:"input" , name:"timeout", label:"超时时间：", validate:"ValidInteger", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:150, inputLeft:25, inputTop:170  },
			   		{ type:"input" , name:"taskParam", label:"任务参数：", validate:"ValidInteger", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:200, inputLeft:25, inputTop:220  },
			   		{ type:"select" , name:"exceptionHandleType", label:"超时处理类型：", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:250, inputLeft:25, inputTop:270 },
			   		{ type:"input" , name:"exceptionHandle", label:"超时处理器：", validate:"ValidInteger", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:300, inputLeft:25, inputTop:320  },
			   		{ type:"select" , name:"exceptionHandleType", label:"异常处理类型：", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:350, inputLeft:25, inputTop:370 },
			   		{ type:"input" , name:"exceptionHandle", label:"异常处理器：", validate:"ValidInteger", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:400, inputLeft:25, inputTop:420  },
			   		{ type:"input" , name:"timeout", label:"字段映射：", validate:"ValidInteger", labelWidth:250, labelAlign:"left", labelLeft:25, labelTop:450, inputLeft:25, inputTop:470  },
			   		{ type:"button" , name:"submit", label:"提交", value:"提交", width:"250", inputLeft:25, inputTop:500  }
			   	];

			   	var task_form = new_task.attachForm(task_form_str);
			
			   	new_task.setText('新建任务');
			   	new_task.centerOnScreen();
			   	new_task.denyResize();
			   	new_task.button('park').hide();
			   	new_task.button('minmax1').hide();
		}
	};
  	</script>
  </head>
  <body>
  </body>
</html>

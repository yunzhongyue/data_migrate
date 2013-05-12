(function(){
	// search nested form control
	for (var a in window.dhtmlXForm.prototype.items) {
		window.dhtmlXForm.prototype.items[a].getNode = function(item){return item;};
	}
	window.dhtmlXForm.prototype.getNode = function(name, value){
		if (value != null) name = [name,value]
		return this.doWithItem(name,"getNode");
	};






	var selectNodeInDesigner = function(ev, name){

		var event = ev;

		if(event.skip){//instead of stopPropagation & cancelBubble, to make default components handlers work
			return;
		}
		event.skip = true;

		var target = event.target || event.srcElement;
		if(window.activeControlsAgent.elements[name].type == "grid" && target.nodeName.toLowerCase() == "td"){
			window.activeControlsAgent.selectInDesigner(name, target._cellIndex);
			window.activeControlsAgent.select(name, target._cellIndex);
		}else{
			window.activeControlsAgent.selectInDesigner(name);
			window.activeControlsAgent.select(name);
		}


	};
	window.activeControlsAgent = {
		supported_controls:{
			layout	:true,
			main_layout:true,
			accordion:true,
			grid	:true,
			form	:true,
			chart	:true,
			data_view:true,
			menu	:true,
			scheduler:true,
			toolbar	:true,
			status	:true,
			tabbar	:true,
			tree	:true,
			win		:true,
			cell	:true,
			panel	:true,
			footer_line:true,
			header_line:true,

			ajax_content:true,
			iframe:true,
			menuitem:true ,
			menuitem_separator:true ,
			tab:true,
			button_text:true,
			button_separator:true,
			button_select:true,
			button_input:true,
			button_2state:true,
			button_normal:true,

			form_fieldset:true,
			form_template:true,
			form_checkbox:true,
			form_radio:true,
			form_input:true,
			form_select:true,
			form_password:true,
			form_label:true,
			form_file:true,
			form_button:true,
			form_btn2state:true,
			form_multiselect:true,
			form_block:true,
			form_calendar:true,
			form_combo:true,
			form_editor:true,
			form_colorpicker:true,
			form_uploader:true
		},
		elements:{},
		selectInDesigner:function(name, index){
			window.parent.des.activeControls.skip = true;
			window.parent.des.activeControls.select(name, index);
			window.parent.des.activeControls.skip = false;
		},
		unselect:function(){
			if(this._highlighter){
				for(var i=0; i<this._highlighter.length; i++ ){
					this._highlighter[i].parentNode.removeChild(this._highlighter[i]);
				}
			}
			window.activeControlsAgent._selectedItem = null;
			this._highlighter = [];
		},
		getZIndex:function(node){
			var index=0;
			while(!index && node && node.parentNode){
				index = this._getZIndex(node);
				node = node.parentNode;
			}
			return index;
		},
		_getZIndex:function(node){
			var style , zIndex;
			if (node.currentStyle) {
				style = node.currentStyle;
			}
			else if (document.defaultView && document.defaultView.getComputedStyle) {
					style = document.defaultView.getComputedStyle(node,"");
			}
			if (style) {
				zIndex = style.zIndex*1;
			} else {
				zIndex = node.style.zIndex*1;
			}
			return zIndex;
		},
		select:function(name, columnIndex){
			if(!this.elements[name])
				return;

			      /* get z-index*/
			var node = this.elements[name].obj;
			var zIndex = this.getZIndex(node);

			   /* get z-index*/
			if(node){
				var coordinate = this.getCoordinates(node);
				if(columnIndex !== undefined){//grid column
					var cell = node.getElementsByTagName("tr");
					var row = null;
					for(var i=0; i < cell.length; i++){
						var dat = cell[i].getElementsByTagName("td")
						if(dat.length){
							row = dat;
							break;
						}
					}
					if(row){
						if(row[columnIndex]){
							var tmp_coord = this.getCoordinates(row[columnIndex]);
							coordinate.x = tmp_coord.x;
							coordinate.width = tmp_coord.width;
						}
					}
				}


				this._attachHighlighters(coordinate, zIndex);
				window.activeControlsAgent._selectedItem ={name:name, index:columnIndex};
			}
		},
		_attachHighlighters:function(coord, zIndex){
			this.unselect();

			var items = this.createHighlighters(coord, zIndex);
				for(var i = 0; i < items.length; i++){
					this._highlighter.push(items[i]);
					document.body.appendChild(items[i]);
			}
		},
		_attachControl:function(controlName, controlNode){
			window.dhtmlxEvent(controlNode, "mousedown", function(event){selectNodeInDesigner(event, controlName);});
			window.dhtmlxEvent(controlNode, "click", function(event){selectNodeInDesigner(event, controlName);});



		},

		attach:function(items){
			for(var i =0; i < items.length; i++){
				var contr = items[i];

					if(contr.type == "windows" && contr.object && contr.object._carcass){
						window.dhtmlxEvent(contr.object._carcass, "mouseup", function(event){
							if(window.activeControlsAgent._selectedItem){
								var item = window.activeControlsAgent._selectedItem;
								if(item != null){
									window.setTimeout(function(){

										window.activeControlsAgent.unselect();
										window.activeControlsAgent.select(item.name, item.index);
									}, 10);
								}
							}
						});
					}

				if(this.supported_controls[ contr.type ] && contr.object){
					var obj = this._getContainer(this.supported_controls[contr.type], contr.object);
					this._attachControl(contr.name, obj);

					this.elements[contr.name] = {obj:obj, type:contr.type};
				}
			}
		},
		_getContainer:function(configObj, obj){
			if(!configObj){
				return null;
			}
			if(configObj === true)
				return obj;
		},
		_getElementLeft:function(el){
			return this._getElementOffset(el, "Left");
		},
		_getElementHeight:function(el){

			return this._getElementSize(el, "Height");
		},
		_getElementWidth:function(el){

			return this._getElementSize(el, "Width");
		},
		_getElementSize:function(el, dir){
			return el["offset" + dir];
		},
		_getElementOffset:function(el, dir){
			if(el.className == "item_absolute"){
				if(el.firstChild){
					if(el.firstChild["offset" + dir] < el.lastChild["offset" + dir] ||  el.lastChild.type == "hidden"){
						el = el.firstChild;
					}else{
						el = el.lastChild;
					}
				}
			}
			var pos = el["offset" + dir];
			var tempEl = el.offsetParent;
			while (!!tempEl) {
				pos += tempEl["offset" + dir];
				if(!isNaN(tempEl["scroll" + dir]))
					pos -= tempEl["scroll" + dir];
				tempEl = tempEl.offsetParent;
			}
			return pos;
		},
		_getElementTop:function(el){
			return this._getElementOffset(el, "Top");
		},
		getCoordinates:function(obj){
			return this._getAbsoluteFormItemCoolrdinates(obj);
		},
		_getPos:function(obj){
			var paddingTop = 0;
			if(obj && obj.className && obj.className.indexOf("dhxform_item_label") != -1){
				paddingTop = 4;// as defined in css :(
			}
			var pos = {
				x:this._getElementLeft(obj),
				y:this._getElementTop(obj) + paddingTop,
				width:this._getElementWidth(obj),
				height:this._getElementHeight(obj) - paddingTop
			};
			if(Math.abs(pos.width) - paddingTop > 0 && Math.abs(pos.height) - paddingTop > 0){
				return pos;
			}else{
				return null;
			}
		},
		_getAbsoluteFormItemCoolrdinates:function(obj){
			var children = obj.childNodes;
			var coordinates = [];

			if(!((obj.type && obj.type.toLowerCase() == 'hidden') || (obj.style && obj.style.display == 'none'))){
				var pos = this._getPos(obj);
				if(pos)
					coordinates.push(pos);
			}
			if(coordinates.length){
				return coordinates.pop();
			}



			for(var i=0; i < children.length; i++){
				if(!((children[i].type && children[i].type.toLowerCase() == 'hidden') || (children[i].style && children[i].style.display == 'none'))){
					var pos = this._getPos(children[i]);
					if(pos)
						coordinates.push(pos);
				}
			}

			if(!coordinates.length){
				coordinates.push({x:0,y:0,width:0,height:0});
			}

			function getParam(paramName, coordinates, max){
				coordinates.sort(function(a,b){
					if(a[paramName] > b[paramName])
						return 1;
					return -1;
				});
				if(!max)
					return coordinates[0][paramName];
				else
					return coordinates[coordinates.length - 1][paramName];
			}
			coordinates.sort(function(a,b){
				if(a.x > b.x)
					return 1;
				return -1;
			});
			var result ={};
			result.x = getParam('x', coordinates);

			result.width = coordinates[coordinates.length - 1].x - coordinates[0].x;
			result.width += getParam('width', coordinates, true);
			//result.width = getParam('width', coordinates, true);

			result.y = getParam('y', coordinates);
			result.height = coordinates[coordinates.length - 1].y - coordinates[0].y;
			result.height += getParam('height', coordinates, true);
			//result.height = getParam('height', coordinates, true);
			return result;

		},

		createHighlighters:function(coordinates, zIndex){
			var line_weight = 1;
			var poss_correction = +1;

			var borders = [];

			for(var i=0; i < 4; i++){
				var hl = document.createElement("div");
				hl.className = "active_ev_highlighter";
				if(zIndex){
					hl.style.zIndex = zIndex*1 + 1;
				}
				borders.push(hl);
			}
			var hor = [0,2];
			for(var i = 0; i < hor.length; i++){

				borders[hor[i]].style.height = line_weight+ 'px';
				borders[hor[i]].style.width = coordinates.width + line_weight + poss_correction +'px';
			}
			borders[0].style.left = coordinates.x - poss_correction + 'px';
			borders[2].style.left = coordinates.x - poss_correction + 'px';

			borders[0].style.top =  coordinates.y - poss_correction  + 'px';
			borders[2].style.top =  coordinates.y + poss_correction + coordinates.height + 'px';

			var vert = [1,3];
			for(var i = 0; i < vert.length; i++){
				borders[vert[i]].style.height = coordinates.height + line_weight+ 2*poss_correction + 'px';
				borders[vert[i]].style.width = line_weight+ 'px';
			}
			borders[1].style.left =  coordinates.x - poss_correction  + 'px';
			borders[3].style.left =  coordinates.x + line_weight + coordinates.width + 'px';

			borders[1].style.top =  coordinates.y - poss_correction  + 'px';
			borders[3].style.top =  coordinates.y - poss_correction + 'px';



			return borders;
		}
	};


})();



function _deserializeItem(item, obj){
	if(item.name && !item.parent){
		switch (item.type){
			case "layout":
			case "main_layout":
				item.object = obj.obj;
				break;
			case "accordion":
			case "menu":
				item.object = obj.base;
				break;
			case "form":
			case "toolbar":
				item.object = obj.cont;
				break;
			case "tabbar":
			case "grid":
				item.object = obj.entBox;
				break;
			case "chart":
			case "data_view":
				item.object = document.getElementById(obj.config.container);
				break;
			case "status":
				item.object = obj.firstChild;
				break;
			case "scheduler":
				item.object = obj._obj;
				break;
			case "tree":
				item.object = obj.allTree;
				break;
			default:
				item.object = obj;
				break;
		}
	}else{
		if(item.parent_name){
			switch(item.parent){
				case "layout":
				case "main_layout":
				case "accordion":
					item.object = obj.cells(item.local_name || item.name);
					break;
				case "tabbar":
					item.object = obj._tabs[item.name];
					break;
				case "toolbar":
					for(var els in obj.objPull){
						if(endsWith(els, item.name)){
							item.object = obj.objPull[els].obj;
							break;
						}
					}
					break;

				case "menu":
					if(item.type == "menuitem_separator"){
						var prefix = "separator_"+obj.idPrefix;
					}else{
						var prefix = obj.idPrefix;
					}
					if(obj.idPull[prefix + item.name])
						item.object = obj.idPull[prefix + item.name];
					break;
				case "form":
					if(item.type == "form_radio"){
						item.object = obj.getNode(item.group_name, item.value);
					}else{
						item.object = obj.getNode(item.name);
					}

					if(item.type == "form_button" && item.object){
						item.object = item.object.firstChild;
					}
					break;
				default:
					break;
			}

		}
	}
	return item;
}


function endsWith(string, substring){
    return string.length >= substring.length && string.substr(string.length - substring.length) == substring;
}


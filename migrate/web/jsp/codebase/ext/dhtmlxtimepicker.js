/*_TOPICS_    
@0:Initialization
@1:Format control
@2:Add/delete
@4:Appearence control
@5:Event Handlers
@6:Selection control
*/

/**
*  @desc: dhtmlXTimePicker constructor
*  @type: public
*  @topic: 0
*/

if(!window.clearInterval)
	window.clearInterval = function(){};
if(window.place_holder_pos === undefined)
	window.place_holder_pos = 0;

function dhtmlXTimePicker () {
  this._tf = '%H:%i:%s';// default timeformat; 
  this._bc = [] ; // body cells
  this.doOnClick = null;
  this._bcNames = ['timeFormat', 'hours', 'minutes', 'seconds', 'btnApply', 'btnCancel'];
  this.hiddenSeconds = false;
  this.create ();
  this.reset();
}
                                                            
dhtmlXTimePicker.prototype={
  /**
  * @desc: reset time. set time to 12:00:00
  * @type: public
  * @topic: 0
  */
  reset:function () {
    this._time = new Date (2008,1,1,12,0,0); //set time by default
    return this.setTime ();
  },
  /**
  *  @desc: create structure
  *  @type: public
  *  @topic: 0
  */   
  create:function () {
    this.entBox = document.createElement ('div');
    this.entBox.className = 'dhtmlxtimepicker';
    this.entBox.onclick = function (e) {
      e = e||event;
      if (e.stopPropagation) e.stopPropagation();
      else e.cancelBubble = true;
    };
    var _bcCount = this._bcNames.length;
    var _self = this;
	  for (var i = 0; i < _bcCount; i++) {
      if (this._bcNames [i] == 'minutes' || this._bcNames [i] == 'seconds') 
      {
        var delimiter = document.createElement ('div');
        delimiter.appendChild (document.createTextNode (':'));
        delimiter.className = 'delimiter';
        this.entBox.appendChild (delimiter);
      }
		  (this._bc [this._bcNames [i]] = document.createElement ('div')).appendChild (document.createTextNode (''));
      this._bc [this._bcNames [i]].className = this._bcNames [i];
      
      this.entBox.appendChild (this._bc [this._bcNames [i]]);
      if (this._bcNames [i] == 'hours' || this._bcNames [i] == 'minutes' || this._bcNames [i] == 'seconds') 
      {
        var upChanger = document.createElement ('div');
        var downChanger = document.createElement ('div');
        (function (bcName) 
        {
          _self._bc [bcName].onclick = function () {
            this.childNodes [1].onblur = this.childNodes [1].onclick;
            this.childNodes [1].style.display = 'block'; 
          };
          var select = document.createElement ('select');
          select.size = 7;
          select.onclick = select.onblur = function (e) {
            (e || event).cancelBubble = true;
            this.style.display = 'none';
            this.onblur = null;
            _self._changeTime (bcName, this.value, 0);
            if (_self.doOnClick) _self.doOnClick ();
          };
          var optCount = bcName == 'hours' ? 24 : 60;
          for (var j = 0; j < optCount; j++) {
            var option = document.createElement ('option');
            option.appendChild (document.createTextNode (j));
            option.value = j;
            select.appendChild (option);
          }
          _self._bc [bcName].appendChild (select);
        
        
          upChanger.onclick = function () 
          { 
            _self._changeTime (bcName, 1, 1);
            if (_self.doOnClick) _self.doOnClick ();
          };
          upChanger.onmousedown = function () 
          {
            window.clearInterval (_self.i);
            _self.i = window.setInterval (function () {_self._changeTime (bcName, 1, 1);}, 200);
            _self.__onmouseup = document.body.onmouseup;
            this.onmouseup = document.body.onmouseup = function () 
            {
              document.body.onmouseup = _self.__onmouseup;
              window.clearInterval (_self.i);
            };
            this.onmouseout = this.onmouseup;
          };
          
          downChanger.onclick = function () 
          { 
            _self._changeTime (bcName, 1, -1);
            if (_self.doOnClick) _self.doOnClick ();
          };
          downChanger.onmousedown = function () 
          {
            window.clearInterval (_self.i);
            _self.i = window.setInterval (function () {_self._changeTime (bcName, 1, -1);}, 200);
            _self.__onmouseup = document.body.onmouseup;
            this.onmouseup = document.body.onmouseup = function () 
            {
              document.body.onmouseup = _self.__onmouseup;
              window.clearInterval (_self.i);
            };
            this.onmouseout = this.onmouseup;
          };
        }) (_self._bcNames [i]);
        
        var changer = document.createElement ('div');
        changer.className = 'changer';
        changer.appendChild (upChanger);
        changer.appendChild (downChanger);
        
        this.entBox.appendChild (changer);
      }
	  }

	  this._setBCValue ('timeFormat', this._tf);
	  this._setBCValue ('btnApply', 'V');
	  this._setBCValue ('btnCancel', 'X');
	  
	  //Change type of timeformat handler
	  this._bc ['timeFormat'].onclick = function (){/*_self.setTimeFormat()*/};
	  
	  document.body.appendChild (this.entBox);
  },

  /**
  *  @desc: set time to timePicker
  *  @param: val - new time value
  *  @type: public
  *  @topic: 4
  */
  setTime:function (val) {
	var time;    if (!val || isNaN((time = new Date()).setTime (val)))
      time = this._time;
   
    this._time = time;
    var h = time.getHours ();
    var m = time.getMinutes ();
    var s = time.getSeconds ();
    /*
    if (this._tf != '24')
  	  if (h > 12) 
			  h -= 12
  	  else if (h == 0) 
			  h = 12
    */
    this._setBCValue('hours', (h < 10 ? '0' + h : h));
    this._setBCValue('minutes', (m < 10 ? '0' + m : m));
    this._setBCValue('seconds', (s < 10 ? '0' + s : s));
    return this._time;
  },
  
  /**
  @desc: set formated date
  @param: format - time format
  @param: time
  @type: public
  @topic: 2
  */
  setFormatedTime:function (format, time) {
    if (!time) return; 
    format = format || this._tf;
    
    var 
    time_start_pos = format.length,
    time_finish_pos = 0,
    place_holders = ['H','h','i','s'];
    
    for (var i=0; i < 4; i++) {
      window.place_holder_pos = format.indexOf(place_holders[i]);
      if (window.place_holder_pos != -1) {
        time_start_pos = Math.min(time_start_pos, window.place_holder_pos);
        time_finish_pos = Math.max(time_finish_pos, window.place_holder_pos);
      }
    }
    
    var time_format = format.substr(time_start_pos-1,time_finish_pos-time_start_pos+2);
    var time_re = new RegExp (time_format.replace(/%[Hhis]/g,"\\d{1,2}"));
        var res = time_re.exec(time);
    if (res) { 
      time = res[0];
      
      for (var i=0; i < time_format.length ; i++) {
        var _char = time_format.charAt (i, 1);
        if (_char == "%") {
          var _cd = time_format.charAt (i + 1);
          var val = time.toString().substr (i, 2)-0;
          if (isNaN(val))
            continue;
          switch (_cd) {
            case "H":
            case "h":
              this._time.setHours (val);
              break;
            case "i":
              this._time.setMinutes (val);
              break;
            case "s":
              this._time.setSeconds (val);
              break;
			default:
				break;
          }
        }
      }
      return this.setTime ();
    } else {
      return this.reset();
    }
  },


  /**
  *  @desc: set default timeformat
  *  @param: format - timeformat
  *  @type: public
  *  @topic: 2
  */
  setTimeFormat:function (format) {
    /*
    var re = /^24|(A|P)M$/i;
	  if (!re.test (timeFormat))
		  timeFormat = this._tf == '24' ? 'AM' : (this._tf == 'AM' ? 'PM' : '24');
    */
	  this._tf = format;
    //this._setBCValue ('timeFormat', this._tf);
	  this.setTime ();
  },

  /**
  *  @desc: set value to body cell
  *  @param: body cell id
  *  @param: body cell value
  *  @type: private
  */
  _setBCValue: function (id, val) {
    this._bc [id].childNodes [0].nodeValue = val;
  },

  /**
  *  @desc: hours and minutes cells handler. Increase or decrease values
  *  @param: type (hours, minutes, seconds)
  *  @param: value - value of increment
  *  @param: factor (1 || -1)
  *  @type: private
  */
  _changeTime: function (type, value, factor) {
    //obj = this._curObj;
    var t = {h: this._time.getHours (),
             m: this._time.getMinutes (),
             s: this._time.getSeconds ()};
    var cur = t [type.substr (0,1)];

    if (type == 'hours')
      this._timeChange = 60 * 60;
    else if (type == 'minutes') 
    {
      this._timeChange = 1 * 60;
      if (t.m * factor == 59 || (t.m + 1) * factor == -1)
        this._timeChange -= 60 * 60;
    }
    else if (type == 'seconds') 
    {
      this._timeChange = 1;
      if (t.s * factor == 59 || (t.s + 1) * factor == -1)
        this._timeChange -= 60;
    }
    
    this._timeChange *= factor ? factor : value-cur;
    this.setTime (this._time.getTime () + this._timeChange * 1000);
  },

  /**
  * @desc: get current time as date object
  * @return: date object
  */
  getTime: function () {
    return this._time;
  },

  /**
  *  @desc: return formated time
  *  @param: format, available placeholders
  *  %H - full hours representation (24h)
  *  %h - AM/PM hours representation
  *  %i - minutes
  *  %s - seconds
  *  %f - time format (AP|PM)
  *  @param: time 
  *  @type: public
  *  @topic: 2
  */
  getFormatedTime: function (format, time) {
      if (!time || isNaN((time = new Date()).setTime (time)))
      time = this._time;
    format = format || this._tf;  

    var formatedTime = (format || this._tf).replace(/%(.{1})/g, function (a, b) {
      switch (b) {
        case 'H' :
          var h = time.getHours ();
          return (h < 10 ? '0' : '') + h;
        case 'h' :
          var h = time.getHours ();
          return (h && (h%13) < 10 ? '0' : '') + ((h - (h > 12 ? 12 : 0))||"12");
        case 'i' :
          var m = time.getMinutes ();
          return (m < 10 ? '0' : '') + m;
        case 's' :
          var s = time.getSeconds ();
          return (s < 10 ? '0' : '') + s;
        case 'f' :
          return ((time.getHours()+1)%24) > 12 ? 'PM' : 'AM';
        default :
          return b;                                                                                  
      }
    });

    return formatedTime;
  },

  /**
  *  @desc: set position
  *  @param: x - left 
  *  @param: y - top
  *  @type: public
  *  @topic: 4
  */
  setPosition:function (x, y) {  
      this.entBox.style.position = 'absolute';
      this.entBox.style.left = x + 'px';
      this.entBox.style.top = y + 'px';
  },

  /**
  * @desc: show
  * @type: public
  * @topic: 4
  */
  show:function () {
    this.entBox.style.display = 'block';
    if (this.hiddenSeconds) 
      this.hideSeconds ();
  },

  /**
  * @desc: show
  * @type: public
  * @topic: 4
  */
  hide:function () {
    this.entBox.style.display = 'none';
  },

  /**
  * @desc: hide seconds
  * @type: public
  * @topic: 4
  */
  hideSeconds:function () {
    if (this.hiddenSeconds) {
      this._bc ['seconds'].style.display = 'none';
      this._bc ['seconds'].previousSibling.style.display = 'none';
      this._bc ['seconds'].nextSibling.style.display = 'none';                                                                                       
      this._bc ['hours'].style.marginLeft = ((this._bc ['minutes'].offsetLeft - this._bc ['hours'].offsetLeft) / 2 - 0) + 'px';
    }
    this.hiddenSeconds = !this.hiddenSeconds ;
  },

  /**
  * @dsec: set onclick handler 
  * @type: public
  * @topic: 4
  */
  setOnClickHandler:function (func) {
    this.doOnClick = func;
  }
};

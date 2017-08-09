/// <reference path="jquery.min.js" />

String.prototype.format = function (args) {
    if (arguments.length > 0) {
        var result = this;
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] == undefined) {
                    return "";
                }
                else {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
        return result;
    }
    else {
        return this;
    }
}

//String.prototype.ToUnicode = function () {
//    return escape(this).toLocaleLowerCase().replace(/%u/gi, '\\u');
//}

//String.prototype.ToGB2312 = function () {
//    return unescape(this.replace(/\\u/gi, '%u'));
//}

//Array.prototype.remove = function (val) { var index = this.indexOf(val); if (index > -1) { this.splice(index, 1); } };



$(function () {
    //备份jquery的ajax方法  
    var _ajax = $.ajax;

    //重写jquery的ajax方法  
    $.ajax = function (opt) {
        opt.timeout = 20000;
        //备份opt中error和success方法  
        var fn = {
            error: function (XMLHttpRequest, textStatus, errorThrown) { },
            success: function (data, textStatus) { }
        }
        if (opt.error) {
            fn.error = opt.error;
        }
        if (opt.success) {
            fn.success = opt.success;
        }
        var isLoading = Util.GetQueryString("isLoading", opt.url);

        if (!isLoading) {
            if (opt.data && opt.data.hasOwnProperty("isLoading")) {
                isLoading = opt.data.isLoading
            }
        }

        var loading = null;

        if (!isLoading || parseInt(isLoading) != 0)
            loading = top.layer.msg('正在请求服务器', { icon: 16, shade: [0.5, '#393D49'], time: 0 });

        //扩展增强处理  
        var _opt = $.extend(opt, {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //错误方法增强处理  
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },
            success: function (data, textStatus) {
                //判断是否为Json字符串
                if (Util.isString(data) && data.indexOf("{") == 0)
                    data = JSON.parse(data);
                if (data && data.code != null) {
                	console.log(data)
                    //成功回调方法增强处理  
                    if (data.code != '200') {
                        Util.AlertInfo(data.msg);
                        return false;
                    }
                    fn.success(data.Data, textStatus);
                    top.layer.close(loading);
                }
                else
                    fn.success(data, textStatus);
            },
            complete: function () {
                top.layer.close(loading);
            }
        });
        return _ajax(_opt);
    };

    //绑定表单数据
    $.fn.FormBindData = function (data) {
        var $controls = this.find("input[name],select[name],textarea[name]");
        for (var i = 0; i < $controls.length; i++) {
            var $control = $($controls[i]);
            var name = $control.attr("name");

            if (!name)
                continue;

            var val = data[name];

            var htmlType = $control.attr("type");
            if (htmlType == "radio") {
                $control.attr("checked", true);
            }
            else if (htmlType == "checkbox") {
                if (val) {
                    var vals = val.toString().split(",");
                    //如果为单个复选框0代表取消选中，1代表选中
                    if (vals.length == 1) {
                        if (vals[0] == true || vals[0] == 1) {
                            $control.attr('checked', true);
                            $control.iCheck('check');
                        }
                        else {
                            $control.attr("checked", false);
                            $control.iCheck('uncheck');
                        }
                    }
                    else {
                        for (var i = 0; i < vals.length; i++) {
                            $control.prop('checked', true);
                            $control.iCheck('check');
                        }
                    }
                } else {
                    $control.attr("checked", false);
                    $control.iCheck('uncheck');
                }
            }
            else {
                $control.val(val);
            }
        }
    }
    //绑定下拉列表数据
    $.fn.ComboxBindData = function (options) {
        var _default = {
            url: "",
            data: [],
            param: {},
            textField: "",
            valueField: "",
            type: "post",
            defaultText: "无",
            defaultValue: -1,
            selectValue: null,
            success: null
        }
        var $control = this;

        options = $.extend(_default, options);

        var bindData = function (data) {
            //清空
            $control.find("option").remove();

            if (options.defaultText) {
                $control.append("<option value='{defaultValue}'>{defaultText}</option>".format(options));
            }

            for (var i = 0; i < data.length; i++) {
                $control.append("<option value='{0}'>{1}</option>".format(data[i][options.valueField], data[i][options.textField]));
            }
            if (options.selectValue) {
                //如果下拉列表不存在选中项，则选中第一项
                if ($control.find("option[value='" + options.selectValue + "']").length > 0)
                    $control.val(options.selectValue);
                else
                    $control.val($control.find("option:eq(0)").val());
            }
            else {
                $control.val($control.find("option:eq(0)").val());
            }
        }

        //是否远程获取
        if (options.url) {
            $.ajax({
                url: options.url,
                data: options.param,
                type: options.type,
                success: function (data) {
                    bindData(data);

                    if (options.success)
                        options.success(data);

                    //$control.chosen();
                }
            });
        }
        else if (options.data) {
            bindData(options.data);
        }

        return $control;
    };

    //序列化表单为Json
    $.fn.SerializeJson = function () {
        var serializeObj = {};
        var serializeArray = [];

        var isArray = false;

        var objs = $(this).find("input,select,textarea");


        var assembleArray = function (arrayObj) {
            var isCreateObj = true

            for (var j = 0; j < arrayObj.length; j++) {

                if (!arrayObj[j].hasOwnProperty(name)) {
                    arrayObj[j][name] = value;
                    isCreateObj = false;
                    break;
                }
            }

            if (isCreateObj) {
                arrayObj.push({});
                arrayObj[arrayObj.length - 1][name] = value;
            }
            return arrayObj;
        }

        for (var i = 0; i < objs.length; i++) {

            var $obj = $(objs[i]);

            var name = $obj.attr("name");

            if (!name) {
                continue;
            }

            var value = "";
            if ($obj.is("textarea")) {
                value = $obj.text();
            }
            if ($obj.is(":checkbox")) {
                value = $obj.is(":checked") ? 1 : 0;
            }
            else {
                value = $obj.val();
            }


            var arrayName = $obj.parents("[data-arrayfiled]").attr("data-arrayfiled");


            if (arrayName) {
                if (!serializeObj.hasOwnProperty(arrayName)) {
                    serializeObj[arrayName] = [];
                    serializeObj[arrayName][0] = {};
                    serializeObj[arrayName][0][name] = value;
                }
                else {

                    serializeObj[arrayName] = assembleArray(serializeObj[arrayName]);
                }
            }
            else {
                if (!serializeObj.hasOwnProperty(name))
                    serializeObj[name] = value;
                else {
                    if (serializeArray.length == 0)
                        serializeArray.push(serializeObj);
                    serializeArray = assembleArray(serializeArray);
                    isArray = true;
                }
            }
        }
        if (isArray)
            return serializeArray;
        return serializeObj;
    };
});


var Util = {
    //获取Json数组中某列的值,以,号隔开
    GetValuesByObjs: function (objs, pName) {
        if (!objs || objs.length < 1)
            return null;
        var array = "";
        for (var i in objs) {
            array += objs[i][pName] + ",";
        }
        //去除末尾,号
        array = array.substring(0, array.length - 1);
        return array;
    },
    //获取Json数组中第一条符合条件的Json对象
    FirstDefault: function (array, pName, pValue) {
        var result = null;
        if (!array || array.length < 1)
            return null;

        for (var i in array) {
            var value = array[i][pName];
            if (value == pValue) {
                result = array[i];
                break;
            }
        }
        return result;
    },
    //获取所有满足条件的Json对象
    FindAll: function (array, pName, pValue) {
        var result = [];
        if (!array || array.length < 1)
            return null;

        for (var i = 0; i < array.length; i++) {
            var value = array[i][pName];
            if (value == pValue)
                result.push(array[i]);
        }
        return result;
    },
    //通过name为控件赋值
    SetValue: function (name, val, dom) {
        if (val != "" && val != null) {
            var htmlType = "";
            //如果传入搜索范围则进行小范围匹配
            if (dom)
                htmlType = dom.find("[name='" + name + "']").attr("type");
            else
                htmlType = $("[name='" + name + "']").attr("type");
            if (htmlType == "text" || htmlType == "select-one" || htmlType == "hidden" || htmlType == "button") {
                $("[name='" + name + "']").val(val);
            } else if (htmlType == "radio") {
                $("input[type=radio][name='" + name + "'][value='" + val + "']").attr("checked", true);
            } else if (htmlType == "checkbox") {
                var vals = val.toString().split(",");
                //如果为单个复选框0代表取消选中，1代表选中
                if (vals.length == 1) {
                    if (parseInt(vals[0]) == 0) {
                        $("input[type=checkbox][name='" + name + "']").attr("checked", false);
                    }
                    else {
                        $("input[type=checkbox][name='" + name + "']").attr("checked", true);
                    }
                }
                for (var i = 0; i < vals.length; i++) {
                    $("input[type=checkbox][name='" + name + "'][value='" + vals[i] + "']").attr("checked", true);
                }
            }
            else {
                $("[name='" + name + "']").val(val);
            }
        }
    },
    //获取Url参数
    GetQueryString: function (name, url) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = "";
        if (url) {
            var s = url.indexOf("?");
            var search = url.substring(s + 1);
            r = search.match(reg);
        }
        else {
            r = window.location.search.substr(1).match(reg);
        }
        if (r != null) return unescape(r[2]); return null;
    },
    //成功提示
    AlertSuccess: function (message) {
        if (swal)
            swal("操作提示", message, "success");
        return false;
    },
    //异常提示
    AlertError: function (message) {
        if (swal)
            swal("异常提示", message, "error");
        return false;
    },
    //警告提醒
    AlertInfo: function (message) {
        if (swal)
            swal("系统提示", message, "error");
        return false;
    },
    //对话框
    ConfirmInfo: function (message, callback) {
        if (swal)
            swal({
                title: "请注意！",
                text: message,
                type: "warning",
                showCancelButton: true,
                cancelButtonText: "取消",
                closeOnConfirm: false,
                confirmButtonText: "是的",
                confirmButtonColor: "#ec6c62"
            }, function (flag) { if (flag) { swal.close(); callback(); } });
    },
    //Ajax结果解析
    AjaxResultAnalytic: function (data) {
        if (data && data.Status != null) {
            //成功回调方法增强处理  
            if (data.Status == 1) {
                Util.AlertError(data.Message);
                swal("发生异常", data.Message, "error");
                return false;
            }
            else if (data.Status == 2) {
                Util.AlertInfo(data.Message);
                return false;
            }
            return data.Data;
        }
        return data;
    },
    //排序
    JsonSort: function (json, key) {
        for (var j = 1, jl = json.length; j < jl; j++) {
            var temp = json[j],
                val = temp[key],
                i = j - 1;
            while (i >= 0 && json[i][key] > val) {
                json[i + 1] = json[i];
                i = i - 1;
            }
            json[i + 1] = temp;

        }
        return json;
    },
    //获取所有值为value的值
    JsonGet: function (json, key, value) {
        var array = [];
        for (var i = 0; i < json.length; i++) {
            var itemValue = json[i][key];
            if (itemValue == value)
                array.push(json[i]);
        }
        return array;
    },
    //数据分级排序
    DataLevelSort: function (options) {
        var _default = {
            data: [],
            parentField: "",
            childField: "",
            sortField: "",
            sortType: "",
            value: -1
        };
        options = $.extend(_default, options);

        var getData = function (parentValue, result, level) {
            //结果容器
            if (!result)
                result = [];
            //计算层级
            if (!level)
                level = 1;

            var jsonArray = Util.JsonSort(Util.JsonGet(options.data, options.parentField, parentValue), options.sortField);

            for (var i = 0; i < jsonArray.length; i++) {
                var item = jsonArray[i];
                item.level = level;
                result.push(jsonArray[i]);
                getData(jsonArray[i][options.childField], result, level + 1);
            }
            if (result.length == options.data.length)
                return result;
        };
        //序列化
        return getData(options.value);
    },
    //数据转为树结构（用于jstree绑定）
    DataTreeSort: function (options) {
        var _default = {
            data: [],
            parentField: "",
            textField: "",
            childField: "",
            valueField: "",
            sortField: "",
            sortType: "",
            value: -1
        };
        options = $.extend(_default, options);

        var getData = function (parentValue, nodes) {

            var jsonArray = Util.JsonGet(options.data, options.parentField, parentValue);

            if (jsonArray == null || jsonArray.length == 0) {
                return nodes;
            }

            if (options.sortField)
                jsonArray = Util.JsonSort(jsonArray, options.sortField);

            for (var i = 0; i < jsonArray.length; i++) {
                var node = {};
                node.value = jsonArray[i][options.valueField];
                node.text = jsonArray[i][options.textField];
                node.children = getData(jsonArray[i][options.childField], []);
                node = $.extend(node, jsonArray[i]);
                nodes.push(node);
            }
            return nodes;
        };


        var jsonArray = getData(options.value, []);
        if (jsonArray.length > 0) {
            //展开根节点
            jsonArray[0].state = {};
            jsonArray[0].state.opened = true;
        }
        //序列化
        return jsonArray;
    },
    //对象是否为数组
    IsArray: function (obj) {
        return Object.prototype.toString.call(obj) === '[object Array]';
    }
    ,
    //是否为对象
    IsObject: function (obj) {
        return typeof (obj) == "object" && Object.prototype.toString.call(obj).toLowerCase() == "[object object]" && !obj.length;
    },
    //获取Json属性个数
    GetJsonLength: function (obj) {
        var size = 0, key;
        for (key in obj) {
            if (obj.hasOwnProperty(key)) size++;
        }
        return size;
    },
    //获取Cookie数据
    GetCookie: function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    },
    //设置Cookie,time单位：分钟
    SetCookie: function (name, value, time) {
        var exp = new Date();
        exp.setTime(exp.getTime() + time * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    },
    //设置Url参数
    SetUrlParam: function (destiny, par, par_value) {
        if (!destiny)
            destiny = location.href;
        var pattern = par + '=([^&]*)';
        var replaceText = par + '=' + par_value;
        if (destiny.match(pattern)) {
            var tmp = '/\\' + par + '=[^&]*/';
            tmp = destiny.replace(eval(tmp), replaceText);
            return (tmp);
        }
        else {
            if (destiny.match('[\?]')) {
                return destiny + '&' + replaceText;
            }
            else {
                return destiny + '?' + replaceText;
            }
        }
        return destiny + '\n' + par + '\n' + par_value;
    },
    OpenWindow: function (url, title) {
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            shade: 0.8,
            area: ['50%', '60%'],
            content: url
        });
    },
    //设置左边菜单未处理数量
    SetLeftNavNumber: function (navCode, number) {
        var $numberControl = $(".J_menuItem[name='{0}']".format(navCode), window.parent.document).find(".label-number");
        var currentNumber = parseInt($numberControl.text());
        if (!isNaN(currentNumber))
            $numberControl.text(currentNumber + number);
        else
            $numberControl.text(number);
    },
    Notify: function (icon, title, msg) {
        if (window.Notification && Notification.permission !== "granted") {
            Notification.requestPermission(function (status) {
                if (Notification.permission !== status) {
                    Notification.permission = status;
                }
            });
        }
        var iconPath = '/Areas/SysManage/Content/img/icons/' + (icon || 'info') + '.png';
        var options = {
            lang: 'zh-CN',
            body: msg,
            icon: iconPath
        };
        var notify;
        // If the user agreed to get notified
        if (window.Notification && Notification.permission === "granted") {
            notify = new Notification(title, options);
        }
        else if (window.Notification && Notification.permission !== "denied") {
            Notification.requestPermission(function (status) {
                if (Notification.permission !== status) {
                    Notification.permission = status;
                }
                if (status === "granted") {
                    notify = new Notification(title, options);
                }
                else {
                    toastr.options.hideDuration = 8000;
                    toastr.success(options.body)
                    //console.log('您禁止了桌面通知，无法推送到您的桌面！');
                }
            });
        }
        else {
            toastr.options.hideDuration = 8000;
            toastr.success(options.body)
            //console.log('您禁止了桌面通知，无法推送到您的桌面！');
        }
        if (notify) {
            notify.onclose = function (evt) {
            };
            //点击切换到浏览器
            notify.onclick = function () {
                window.focus();
            };
        }
    },
    isString: function (str) {
        return (typeof str == 'string') && str.constructor == String;
    }
};



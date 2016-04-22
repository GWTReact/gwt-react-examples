GWTReact = {};

GWTReact.makeSpec = function(componentObj) {
    var prototype = componentObj.__proto__;
    var spec = {
        getState: function() {
            return this.state;
        }
        ,getProps: function() {
            return this.props;
        },
        getRef: function(refId) {
            return this.refs[refId];
        }
    };

    for (var p in prototype) {
        if (p != 'constructor' && prototype.hasOwnProperty(p)) {
            spec[p] = prototype[p];
            // console.log("proto fn " + p);
        }
    }

    for (p in componentObj) {
        if (p != 'constructor' && componentObj.hasOwnProperty(p)) {
            spec[p] = componentObj[p];
            //console.log("obj prop " + p);
        }
    }
    //console.log("");
    return spec;
}

GWTReact.$get = function(obj, field) {
    return obj[field];
}

GWTReact.$set = function(obj, field, val) {
    obj[field] = val;
}

GWTReact.cast = function(obj) {
    return obj;
}

GWTReact.merge = function(obj1, obj2) {
    return Object.assign({}, obj1, obj2);
}

GWTReact.except = function(obj, exclude) {
    var out = {};
    var exludeMap = {};

    for(i = 0; i < exclude.length; i++) {
        exludeMap[exclude[i]] = true;
    }

    for (var p in obj) {
        if (!exludeMap[p] && obj.hasOwnProperty(p)) {
            out[p] = obj[p];
        }
    }

    return out;
}

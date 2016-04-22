(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define([], factory);
	else if(typeof exports === 'object')
		exports["ReduxUndo"] = factory();
	else
		root["ReduxUndo"] = factory();
})(this, function() {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports) {

	'use strict';

	exports.__esModule = true;

	var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

	exports.parseActions = parseActions;
	exports.default = undoable;
	exports.isHistory = isHistory;
	exports.distinctState = distinctState;
	exports.includeAction = includeAction;
	exports.excludeAction = excludeAction;

	function _toConsumableArray(arr) { if (Array.isArray(arr)) { for (var i = 0, arr2 = Array(arr.length); i < arr.length; i++) { arr2[i] = arr[i]; } return arr2; } else { return Array.from(arr); } }

	// debug output
	var __DEBUG__ = void 0;
	function debug() {
	  /* istanbul ignore if */
	  if (__DEBUG__) {
	    var _console;

	    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
	      args[_key] = arguments[_key];
	    }

	    if (!console.group) {
	      args.unshift('%credux-undo', 'font-style: italic');
	    }
	    (_console = console).log.apply(_console, args);
	  }
	}
	function debugStart(action, state) {
	  /* istanbul ignore if */
	  if (__DEBUG__) {
	    var args = ['action', action.type];
	    if (console.group) {
	      var _console2;

	      args.unshift('%credux-undo', 'font-style: italic');
	      (_console2 = console).groupCollapsed.apply(_console2, args);
	      console.log('received', { state: state, action: action });
	    } else {
	      debug.apply(undefined, args);
	    }
	  }
	}
	function debugEnd() {
	  /* istanbul ignore if */
	  if (__DEBUG__) {
	    return console.groupEnd && console.groupEnd();
	  }
	}
	// /debug output

	// action types
	var ActionTypes = exports.ActionTypes = {
	  UNDO: '@@redux-undo/UNDO',
	  REDO: '@@redux-undo/REDO',
	  JUMP_TO_FUTURE: '@@redux-undo/JUMP_TO_FUTURE',
	  JUMP_TO_PAST: '@@redux-undo/JUMP_TO_PAST',
	  JUMP: '@@redux-undo/JUMP',
	  CLEAR_HISTORY: '@@redux-undo/CLEAR_HISTORY'
	};
	// /action types

	// action creators to change the state
	var ActionCreators = exports.ActionCreators = {
	  undo: function undo() {
	    return { type: ActionTypes.UNDO };
	  },
	  redo: function redo() {
	    return { type: ActionTypes.REDO };
	  },
	  jumpToFuture: function jumpToFuture(index) {
	    return { type: ActionTypes.JUMP_TO_FUTURE, index: index };
	  },
	  jumpToPast: function jumpToPast(index) {
	    return { type: ActionTypes.JUMP_TO_PAST, index: index };
	  },
	  jump: function jump(index) {
	    return { type: ActionTypes.JUMP, index: index };
	  },
	  clearHistory: function clearHistory() {
	    return { type: ActionTypes.CLEAR_HISTORY };
	  }
	};
	// /action creators

	// length: get length of history
	function length(history) {
	  var past = history.past;
	  var future = history.future;

	  return past.length + 1 + future.length;
	}
	// /length

	// insert: insert `state` into history, which means adding the current state
	//         into `past`, setting the new `state` as `present` and erasing
	//         the `future`.
	function insert(history, state, limit) {
	  debug('insert', { state: state, history: history, free: limit - length(history) });

	  var past = history.past;
	  var present = history.present;

	  var historyOverflow = limit && length(history) >= limit;

	  return {
	    past: [].concat(_toConsumableArray(past.slice(historyOverflow ? 1 : 0)), [present]),
	    present: state,
	    future: []
	  };
	}
	// /insert

	// undo: go back to the previous point in history
	function undo(history) {
	  debug('undo', { history: history });

	  var past = history.past;
	  var present = history.present;
	  var future = history.future;


	  if (past.length <= 0) return history;

	  return {
	    past: past.slice(0, past.length - 1), // remove last element from past
	    present: past[past.length - 1], // set element as new present
	    future: [present].concat(_toConsumableArray(future))
	  };
	}
	// /undo

	// redo: go to the next point in history
	function redo(history) {
	  debug('redo', { history: history });

	  var past = history.past;
	  var present = history.present;
	  var future = history.future;


	  if (future.length <= 0) return history;

	  return {
	    future: future.slice(1, future.length), // remove element from future
	    present: future[0], // set element as new present
	    past: [].concat(_toConsumableArray(past), [present // old present state is in the past now
	    ])
	  };
	}
	// /redo

	// jumpToFuture: jump to requested index in future history
	function jumpToFuture(history, index) {
	  if (index === 0) return redo(history);
	  if (index < 0 || index >= history.future.length) return history;

	  var past = history.past;
	  var present = history.present;
	  var future = history.future;


	  return {
	    future: future.slice(index + 1),
	    present: future[index],
	    past: past.concat([present]).concat(future.slice(0, index))
	  };
	}
	// /jumpToFuture

	// jumpToPast: jump to requested index in past history
	function jumpToPast(history, index) {
	  if (index === history.past.length - 1) return undo(history);
	  if (index < 0 || index >= history.past.length) return history;

	  var past = history.past;
	  var present = history.present;
	  var future = history.future;


	  return {
	    future: past.slice(index + 1).concat([present]).concat(future),
	    present: past[index],
	    past: past.slice(0, index)
	  };
	}
	// /jumpToPast

	// jump: jump n steps in the past or forward
	function jump(history, n) {
	  if (n > 0) return jumpToFuture(history, n - 1);
	  if (n < 0) return jumpToPast(history, history.past.length + n);
	  return history;
	}
	// /jump

	// createHistory
	function createHistory(state) {
	  return {
	    past: [],
	    present: state,
	    future: []
	  };
	}
	// /createHistory

	// parseActions
	function parseActions(rawActions) {
	  var defaultValue = arguments.length <= 1 || arguments[1] === undefined ? [] : arguments[1];

	  if (Array.isArray(rawActions)) {
	    return rawActions;
	  } else if (typeof rawActions === 'string') {
	    return [rawActions];
	  }
	  return defaultValue;
	}
	// /parseActions

	// redux-undo higher order reducer
	function undoable(reducer) {
	  var rawConfig = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];

	  __DEBUG__ = rawConfig.debug;

	  var config = {
	    initTypes: parseActions(rawConfig.initTypes, ['@@redux-undo/INIT']),
	    limit: rawConfig.limit,
	    filter: rawConfig.filter || function () {
	      return true;
	    },
	    undoType: rawConfig.undoType || ActionTypes.UNDO,
	    redoType: rawConfig.redoType || ActionTypes.REDO,
	    jumpToPastType: rawConfig.jumpToPastType || ActionTypes.JUMP_TO_PAST,
	    jumpToFutureType: rawConfig.jumpToFutureType || ActionTypes.JUMP_TO_FUTURE,
	    jumpType: rawConfig.jumpType || ActionTypes.JUMP,
	    clearHistoryType: rawConfig.clearHistoryType || ActionTypes.CLEAR_HISTORY
	  };

	  return function () {
	    var state = arguments.length <= 0 || arguments[0] === undefined ? config.history : arguments[0];
	    var action = arguments.length <= 1 || arguments[1] === undefined ? {} : arguments[1];

	    debugStart(action, state);

	    var history = state;
	    if (!config.history) {
	      debug('history is uninitialized');

	      if (state === undefined) {
	        history = createHistory(reducer(state, {}));
	        debug('do not initialize on probe actions');
	      } else if (isHistory(state)) {
	        history = config.history = state;
	        debug('initialHistory initialized: initialState is a history', config.history);
	      } else {
	        history = config.history = createHistory(state);
	        debug('initialHistory initialized: initialState is not a history', config.history);
	      }
	    }

	    var res = void 0;
	    switch (action.type) {
	      case undefined:
	        return history;

	      case config.undoType:
	        res = undo(history);
	        debug('after undo', res);
	        debugEnd();
	        return res;

	      case config.redoType:
	        res = redo(history);
	        debug('after redo', res);
	        debugEnd();
	        return res;

	      case config.jumpToPastType:
	        res = jumpToPast(history, action.index);
	        debug('after jumpToPast', res);
	        debugEnd();
	        return res;

	      case config.jumpToFutureType:
	        res = jumpToFuture(history, action.index);
	        debug('after jumpToFuture', res);
	        debugEnd();
	        return res;

	      case config.jumpType:
	        res = jump(history, action.index);
	        debug('after jump', res);
	        debugEnd();
	        return res;

	      case config.clearHistoryType:
	        res = createHistory(history.present);
	        debug('cleared history', res);
	        debugEnd();
	        return res;

	      default:
	        res = reducer(history.present, action);

	        if (config.initTypes.some(function (actionType) {
	          return actionType === action.type;
	        })) {
	          debug('reset history due to init action');
	          debugEnd();
	          return config.history;
	        }

	        if (typeof config.filter === 'function' && !config.filter(action, res, history)) {
	          debug('filter prevented action, not storing it');
	          debugEnd();
	          return _extends({}, history, {
	            present: res
	          });
	        }

	        if (history.present !== res) {
	          history = insert(history, res, config.limit);
	          debug('inserted new state into history');
	        } else {
	          debug('not inserted, history is unchanged');
	        }

	        debug('history: ', history, ' free: ', config.limit - length(history));
	        debugEnd();
	        return history;
	    }
	  };
	}
	// /redux-undo

	// isHistory helper: check for a valid history object
	function isHistory(history) {
	  return typeof history.present !== 'undefined' && typeof history.future !== 'undefined' && typeof history.past !== 'undefined' && Array.isArray(history.future) && Array.isArray(history.past);
	}
	// /isHistory

	// distinctState helper
	/* istanbul ignore next */
	function distinctState() {
	  console.warn('distinctState is deprecated in beta4 and newer. The distinctState behavior is now default, which means only actions resulting in a new state are recorded. See https://github.com/omnidan/redux-undo#filtering-actions for more details.');
	  return function () {
	    return true;
	  };
	}
	// /distinctState

	// includeAction helper
	function includeAction(rawActions) {
	  var actions = parseActions(rawActions);
	  return function (action) {
	    return actions.indexOf(action.type) >= 0;
	  };
	}
	// /includeAction

	// excludeAction helper
	function excludeAction() {
	  var rawActions = arguments.length <= 0 || arguments[0] === undefined ? [] : arguments[0];

	  var actions = parseActions(rawActions);
	  return function (action) {
	    return actions.indexOf(action.type) < 0;
	  };
	}
	// /excludeAction

/***/ }
/******/ ])
});
;
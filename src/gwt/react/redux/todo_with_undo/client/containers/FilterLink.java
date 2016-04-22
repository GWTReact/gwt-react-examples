package gwt.react.redux.todo_with_undo.client.containers;

import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.utils.JSFunc;
import gwt.react.client.utils.ObjLiteral;
import gwt.react.redux.todo_with_undo.client.actions.Actions;
import gwt.react.redux.todo_with_undo.client.components.Link;
import gwt.redux.client.addons.react_redux.MapDispatchToPropsFn;
import gwt.redux.client.addons.react_redux.MapStateToPropsFn;
import gwt.redux.client.addons.react_redux.ReactRedux;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

import static gwt.react.client.utils.ObjLiteral.$;

public class FilterLink {
    @JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
    public static class Props extends Link.Props {
        public String filter;

        @JsOverlay
        final public Props Filter(String filter) {
            this.filter = filter;
            return this;
        }
    }

    private static MapStateToPropsFn<ObjLiteral, Props> mapStateToPropsFn = (state, ownProps) ->
        $(new ObjLiteral(), "active", ownProps.filter.equals(state.getStr("visibilityFilter")));

    private static MapDispatchToPropsFn<Props> mapDispatchToProps = (dispatch, ownProps) -> {
        JSFunc onClick = () -> dispatch.forward(Actions.setVisibilityFilter(ownProps.filter));

        return $(new ObjLiteral(), "onClick", onClick);
    };

    public static StatelessComponent<Props, BaseContext> component =
            ReactRedux.connect(mapStateToPropsFn, mapDispatchToProps).toComponent(Link.component);
}

package gwt.react.redux.todo_with_undo.client.containers;

import gwt.react.client.components.StatelessComponent;
import gwt.react.client.proptypes.BaseContext;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.BtnProps;
import gwt.react.client.utils.JSFunc;
import gwt.react.client.utils.ObjLiteral;
import gwt.react.redux.todo_with_undo.client.containers.FilterLink.Props;
import gwt.react.redux.todo_with_undo.client.reducers.TodoAppReducer;
import gwt.redux.client.addons.react_redux.MapDispatchToPropsFn;
import gwt.redux.client.addons.react_redux.MapStateToPropsFn;
import gwt.redux.client.addons.react_redux.ReactRedux;
import gwt.redux.client.addons.redux_undo.ReduxUndo;

import static gwt.react.client.api.React.DOM.button;
import static gwt.react.client.api.React.DOM.p;
import static gwt.react.client.utils.ObjLiteral.$;

/**
 * This class illustrates how to work with typeless props i.e. no prop class if defined for this component
 */
public class UndoRedo {

    private static StatelessComponent<BaseProps, BaseContext> undoRedoComp = (props, context) ->
        p(null,
            button(new BtnProps()
                    .onClick(props.getObj("onUndo"))
                    .disabled(!props.getBool("canUndo")),
                    "Undo"),
            button(new BtnProps()
                    .onClick(props.getObj("onRedo"))
                    .disabled(!props.getBool("canRedo")),
                    "Redo")
        );

    private static MapStateToPropsFn<TodoAppReducer.State, Props> mapStateToPropsFn = (state, ownProps) ->
            $(new ObjLiteral(),
                    "canUndo", state.todos.past.length() > 0,
                    "canRedo", state.todos.future.length() > 0);

    private static MapDispatchToPropsFn<BaseProps> mapDispatchToProps = (dispatch, ownProps) -> {
        JSFunc onUndo = () -> dispatch.forward(ReduxUndo.ActionCreators.undo());
        JSFunc onRedo = () -> dispatch.forward(ReduxUndo.ActionCreators.redo());

        return $(new ObjLiteral(), "onUndo", onUndo, "onRedo", onRedo);
    };

    public static StatelessComponent<BaseProps, BaseContext> component =
            ReactRedux.connect(mapStateToPropsFn, mapDispatchToProps).toComponent(undoRedoComp);
}
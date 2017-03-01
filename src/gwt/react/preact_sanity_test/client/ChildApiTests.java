package gwt.react.preact_sanity_test.client;

import gwt.interop.utils.shared.collections.Array;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.DOMElement;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseProps;
import gwt.react.client.proptypes.html.CssProps;
import gwt.react.client.proptypes.html.HtmlProps;

import static gwt.react.client.api.GwtReact.castAsReactElement;
import static gwt.react.client.api.React.DOM.br;
import static gwt.react.client.api.React.DOM.div;

class ChildApiTests {
    static DOMElement<HtmlProps> countChildrenComponent(BaseProps props) {
        int countChildren = React.Children.count(props.children);

        return
            div(null,
                div(null, "There are " + countChildren + " child components"),
                castAsReactElement(props.children),
                br(null)
            );
    };

    static DOMElement<HtmlProps> updatePropsOfChildrenComponent(BaseProps props) {

        Array<ReactElement<?, ?>> newChildren = React.Children.map(props.children, (child) -> {
            HtmlProps propsToMerge =  new HtmlProps()
                                            .style(new CssProps()
                                                .color("red"));

            return React.cloneElement(child, propsToMerge);
        });

        return
            div(null,
                castAsReactElement(newChildren),
                br(null));
    };

    static DOMElement<HtmlProps> childArrayTestComponent(BaseProps props) {
        Array<ReactElement<?, ?>> existingChildren = React.Children.toArray(props.children);

        return
            div(null,
                castAsReactElement(existingChildren),
                br(null));
    };
}

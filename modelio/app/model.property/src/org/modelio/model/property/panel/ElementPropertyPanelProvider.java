/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.model.property.panel;

import javax.annotation.PostConstruct;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.ui.panel.IPanelProvider;

/**
 * The ModelPropertyView manages two GUI panels:
 * <ul>
 * <li>the tree panel that displays the selected model element along with the
 * list of the stereotypes that are available on it</li>
 * <li>the content panel that displays the contents of the 'typeItem' selected
 * in the tree panel.</li>
 * </ul>
 * The ModelPropertyPanelProvider aggregates the two panels and controls their
 * behavior so that the two panels do not have to know anything about each
 * other, it also provides services to the view toolbar handlers so that these
 * handlers do not have to deal with the two panels organization.
 * The model element whose type items are currently listed is called the
 * currentElement. The particular stereotype whose contents is currently shown
 * is called the current type item.
 * 
 * @author phv
 */
@objid ("8fa7c870-c068-11e1-8c0a-002564c97630")
public class ElementPropertyPanelProvider implements IPanelProvider {
    /**
     * Tells whether the view is "pinned".
     * <p>
     * A pinned view doesn't update on selection changes or navigation events.
     */
    @objid ("8fa7c87a-c068-11e1-8c0a-002564c97630")
    protected boolean pinned;

    @objid ("a412d003-3ec9-4bc6-b8a5-c10d09c4c329")
    private ElementPropertyController controller;

    @objid ("2ab1432f-2ecd-477f-a967-583715897e69")
    private ElementPropertyUi ui;

    /**
     * Constructor to be used by the Eclipse E4 injection service.
     * For E4 injection only, use #ElementPropertyPanelProvider(IEclipseContext) instead.
     */
    @objid ("869933f7-cf24-11e1-80a9-002564c97630")
    public ElementPropertyPanelProvider() {
        // Empty
    }

    /**
     * Constructor to use if you don't use Eclipse E4 injection.
     * 
     * @param context the E4 context service, null allowed (null => read only panel).
     */
    @objid ("afd40f98-1ad0-46c2-be26-db55f91d28c2")
    public ElementPropertyPanelProvider(IEclipseContext context) {
        if (context != null) {
            ContextInjectionFactory.inject(this, context);
        }
        
        postConstruct(context);
    }

    /**
     * Called by the framework to create the view and initialize it.
     * 
     * @return the SashForm containing the property panel.
     */
    @objid ("8fa7c87e-c068-11e1-8c0a-002564c97630")
    @Override
    public Composite createPanel(Composite parent) {
        this.ui = this.controller.createView(parent);
        return this.ui.getComposite();
    }

    @objid ("78829f67-63c3-4677-9183-8d5eff6e395f")
    @Override
    public void dispose() {
        // Empty
    }

    @objid ("b2bf327c-5bad-449b-a88c-9b97912a9000")
    @Override
    public String getHelpTopic() {
        return null;
    }

    /**
     * Get the current element displayed by the view.
     * 
     * @return the model element whose content is listed in the property panel.
     * May be null.
     */
    @objid ("8faa2997-c068-11e1-8c0a-002564c97630")
    @Override
    public Element getInput() {
        return this.controller.getSelectedElement();
    }

    @objid ("76530813-c677-11e1-8f21-002564c97630")
    @Override
    public Composite getPanel() {
        return this.ui.getComposite();
    }

    @objid ("ee546419-85a4-418b-b413-d9e547a98fc3")
    public TreeViewer getTreeViewer() {
        return this.ui.getTreePanel().getTreeViewer();
    }

    /**
     * Tells whether the view is "pinned".
     * <p>
     * A pinned view doesn't update on selection changes or navigation events.
     * 
     * @return <code>true</code> if the view is pinned, else <code>false</code>.
     */
    @objid ("8faa29c2-c068-11e1-8c0a-002564c97630")
    public boolean isPinned() {
        return this.pinned;
    }

    @objid ("52fca2df-4746-4e85-81b9-f7f07ce6c4ab")
    @Override
    public boolean isRelevantFor(Object obj) {
        if (obj instanceof ISelection) {
            return SelectionHelper.getFirst((ISelection) obj, Element.class) != null;
        } else {
            return obj instanceof Element;
        }
    }

    @objid ("6cf76242-4f95-411f-bc6c-748073600281")
    public void setAutoLayout(boolean value) {
        if (value) {
            this.ui.enableAutoLayout();
        } else {
            this.ui.disableAutoLayout();
        }
    }

    @objid ("8fa7c882-c068-11e1-8c0a-002564c97630")
    public void setHorizontalLayout() {
        this.ui.disableAutoLayout();
        this.ui.setHorizontalLayout();
    }

    /**
     * Set the current element displayed by the view.
     * 
     * @param input the ISelection whose content is listed in the property panel.
     * May be null or empty.
     */
    @objid ("8faa299e-c068-11e1-8c0a-002564c97630")
    @Override
    public void setInput(Object input) {
        Element me = input instanceof Element ? (Element) input : SelectionHelper.getFirst((ISelection) input, Element.class);
        
        if (this.controller != null) {
            // Just delegate to the controller
            this.controller.setInputs(me, null);
        }
    }

    /**
     * Pin or unpin the view.
     * <p>
     * A pinned view doesn't update on selection changes or navigation events.
     * 
     * @param pinned <code>true</code> if the view must be pinned, else
     * <code>false</code>.
     */
    @objid ("06b5b35c-16d1-11e2-aa0d-002564c97630")
    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    @objid ("05a9c9f5-2053-4e1f-8584-f4cee26ee2aa")
    public void setShowHiddenMdaElements(boolean v) {
        this.controller.setShowHiddenMdaElements(v);
    }

    @objid ("8fa7c885-c068-11e1-8c0a-002564c97630")
    public void setVerticalLayout() {
        this.ui.disableAutoLayout();
        this.ui.setVerticalLayout();
    }

    @objid ("af968b88-f000-4f3f-9f6c-25dc3e76246a")
    @PostConstruct
    final void postConstruct(IEclipseContext context) {
        this.controller = new ElementPropertyController(context);
    }

}

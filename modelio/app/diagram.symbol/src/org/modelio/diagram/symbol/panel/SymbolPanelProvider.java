/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.symbol.panel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.style.StyleEditorProxy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.viewer.StyleEditPanel;
import org.modelio.diagram.styles.viewer.StyleEditPanelSelection;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Contains the style edition panel and a toolbar.
 */
@objid ("654552c5-153a-4c3e-b4a2-e91bdb4996ef")
public class SymbolPanelProvider implements IPanelProvider, PropertyChangeListener {
    @objid ("602c61d5-3b69-40fe-8d16-a54e34a38ec1")
    private boolean showHelp = true;

    @objid ("f7e20b47-3bde-4218-bd5d-c5d557cb4145")
    private IGmObject selectedSymbol;

    @objid ("974cd994-a8db-4a78-8c51-8c9c650abc2f")
    private StyleEditPanel styleEditPanel;

    @objid ("b29f64a4-5d18-451e-a10d-fe5842da85e4")
    private ToolBarManager toolBarManager;

    @objid ("1eaad6c6-be20-49d3-bb6e-b89b5d3a0a11")
    private SymbolContributionFactory toolbarContribs;

    @objid ("55c55794-75b8-4222-8fd5-fb172874551a")
    private Composite top;

    /**
     * C'tor
     */
    @objid ("57f2f94b-b80e-44ff-8b98-4ffd298c5ac2")
    public SymbolPanelProvider() {
        this.styleEditPanel = StyleEditPanel.newTablePanel();
    }

    @objid ("5271d2a5-db31-44ae-a5ad-c272951a0f51")
    @Override
    public Object createPanel(Composite parent) {
        // Top level container: a Composite
        this.top = new Composite(parent, SWT.NONE);
        final GridLayout gl = new GridLayout(1, true);
        gl.marginBottom = gl.marginTop = gl.marginHeight = 0;
        gl.marginLeft = gl.marginRight = gl.marginWidth = 0;
        gl.horizontalSpacing = gl.verticalSpacing = 0;
        this.top.setLayout(gl);
        
        // The tool bar
        this.toolBarManager = new ToolBarManager(SWT.HORIZONTAL);
        ToolBar tb = this.toolBarManager.createControl(this.top);
        tb.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true, false));
        
        // The style edit panel
        this.styleEditPanel.createPanel(this.top);
        
        ISymbolPanelModel access = new SymbolModel();
        ISymbolPanelController controller = new SymbolController();
        
        SymbolContributionFactory menuContribs = new SymbolContributionFactory(controller, access);
        MenuManager contextualMenu = this.styleEditPanel.getContextualMenu();
        contextualMenu.add(menuContribs.extractStyleFromSelected);
        contextualMenu.add(menuContribs.updateStyleFromSelected);
        contextualMenu.addMenuListener(manager -> menuContribs.refresh());
        
        this.toolbarContribs = new SymbolContributionFactory(controller, access);
        this.toolBarManager.add(this.toolbarContribs.reset);
        this.toolBarManager.add(this.toolbarContribs.extractStyleFromModified);
        this.toolBarManager.add(this.toolbarContribs.updateStyleFromAllModified);
        this.toolBarManager.add(this.toolbarContribs.showHelp);
        
        this.styleEditPanel.addListener((changedData, isValidate) -> {
            updateToolbar();
        });
        this.styleEditPanel.getViewer().addSelectionChangedListener(ev -> {
            updateToolbar();
        });
        
        this.styleEditPanel.getPanel().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        this.top.addDisposeListener(ev -> dispose());
        return this.top;
    }

    @objid ("c2a583fc-4fd6-4278-be3c-0fc4aecf91d8")
    @Override
    public void dispose() {
        if (this.styleEditPanel != null) {
            this.styleEditPanel.dispose();
            this.styleEditPanel = null;
        }
        if (this.selectedSymbol != null) {
            this.selectedSymbol.removePropertyChangeListener(this);
        }
    }

    @objid ("9519e5a6-cba0-431e-91a5-9eaaac2d2371")
    @Override
    public String getHelpTopic() {
        return this.styleEditPanel.getHelpTopic();
    }

    @objid ("73b2265e-27b3-4aed-807b-50fc668b7b4a")
    @Override
    public Object getInput() {
        return this.styleEditPanel.getInput();
    }

    @objid ("e5cf75cb-35c7-49ff-8e60-56e523502f0d")
    @Override
    public Composite getPanel() {
        return this.top;
    }

    @objid ("2e2f8e34-a0d2-42d5-88b9-3ec14c796723")
    @Override
    public boolean isRelevantFor(Object input) {
        return SelectionHelper.getFirst((ISelection) input, IGmObject.class) != null;
    }

    /**
     * @return <code>true</code> if the help panel is shown.
     */
    @objid ("c18e8a6b-c87c-46cc-b3ac-36b0e2b174a8")
    public boolean isShowHelp() {
        return this.showHelp;
    }

    /**
     * Apply the style selected in the combo to the given style.
     * Setting the style to null means:
     * <ul>
     * <li>setting the cascaded style of the gmObject to the style of the owning diagram (unless the gmObject is the diagram itself)
     * <li>cleaning all local properties
     * </ul>
     * @param style the style to apply.
     */
    @objid ("ac5129ba-55b7-11e2-877f-002564c97630")
    public void onChangeStyle(IStyle style) {
        StyleEditorProxy se = new StyleEditorProxy(this.selectedSymbol);
        
        if (style != null) {
            se.setCascadedStyle(style);
        } else {
            // setting the style to null means:
            // - setting the cascaded style of the gmObject to the style of the owning diagram (unless the gmObject is the diagram itself)
            // - cleaning all local properties
            if (this.selectedSymbol != this.selectedSymbol.getDiagram()) {
                IStyle diagramStyle = this.selectedSymbol.getDiagram().getPersistedStyle();
                se.setCascadedStyle(diagramStyle);
                se.reset();
            }
        
        }
    }

    @objid ("30f573b7-abb4-4151-bc73-d342db1c0816")
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (this.styleEditPanel != null) {
            if (IGmObject.PROPERTY_DELETE.equals(evt.getPropertyName())) {
                // The Gm displayed in the Symbol view has been deleted, reset
                // the selection.
                setSelectedSymbol(null);
            }
            if (IGmObject.PROPERTY_STYLE.equals(evt.getPropertyName())
                    && getSelectedSymbol() instanceof IGmDiagram) {
                // The GmAbstractDiagram Style reference changes on diagram
                // reloading
                setSelectedSymbol(getSelectedSymbol());
            }
        }
    }

    /**
     * input is a ISelection
     */
    @objid ("5c3e129b-9be4-4914-a436-6d061c20ffe5")
    @Override
    public void setInput(Object input) {
        IGmObject gmObject = SelectionHelper.getFirst((ISelection) input, IGmObject.class);
        setSelectedSymbol(gmObject);
        updateToolbar();
    }

    @objid ("46210cc7-98e5-43cb-a146-b02be31ea1b6")
    void setSelectedSymbol(IGmObject newlySelectedSymbol) {
        if (this.selectedSymbol != null
                && this.selectedSymbol == newlySelectedSymbol
                && !(newlySelectedSymbol instanceof IGmDiagram)) {
            // filter IGmDiagram because its getStyle() changes on
            // diagram reloading
            return;
        }
        
        if (this.selectedSymbol != null) {
            this.selectedSymbol.removePropertyChangeListener(this);
        }
        
        this.selectedSymbol = newlySelectedSymbol;
        
        if (newlySelectedSymbol != null && !this.top.isDisposed()) {
            boolean isEditable = newlySelectedSymbol.isUserEditable();
            // Change the StyleViewer model provider
            // Instead of providing the symbol Style, we provide a StyleEditor
            // proxy that will be responsible for managing transactions in the model
            // in case of modifications
        
            final StyleEditPanelUIData data = new StyleEditPanelUIData(
                    newlySelectedSymbol.getSymbolViewModel(),
                    new StyleEditorProxy(newlySelectedSymbol),
                    isEditable);
            IStyle baseStyle = newlySelectedSymbol.getPersistedStyle().getBaseStyle();
            IStyle diagramStyle = newlySelectedSymbol.getDiagram().getPersistedStyle();
        
            if (baseStyle != diagramStyle) {
                data.addCascadedStyle(baseStyle, null);
            } else {
                NamedStyle diagramBaseStyle = (NamedStyle) diagramStyle.getBaseStyle();
        
                data.addCascadedStyle(new StyleEditorProxy(newlySelectedSymbol.getDiagram()), "Diagram");
                if (!diagramBaseStyle.getName().equals("default")) {
                    data.addCascadedStyle(diagramBaseStyle, null);
                }
            }
        
            this.styleEditPanel.setInput(data);
            updateToolbar();
            // this.styleEditPanel.getTreeViewer().expandAll();
            this.styleEditPanel.getViewer().refresh();
        
            // Set the listeners
            this.selectedSymbol.addPropertyChangeListener(this);
        } else {
            this.styleEditPanel.setInput(null);
            updateToolbar();
            // this.styleViewer.getTreeViewer().refresh();
        }
    }

    @objid ("84f27aaf-780f-48be-a20d-d4437d8b811c")
    private StyleEditPanelUIData getData() {
        if (this.styleEditPanel != null) {
            return this.styleEditPanel.getInput();
        } else {
            return null;
        }
    }

    @objid ("866adac4-4d31-4a51-a28f-0b2f23f7fb4c")
    private static NamedStyle getNamedStyle(IStyle s) {
        for (IStyle p = s; p != null; p = p.getCascadedStyle()) {
            if (p instanceof NamedStyle) {
                return (NamedStyle) p;
            }
        }
        throw new IllegalArgumentException(String.format("%s style has no named style in its parent hierarchy.", s));
    }

    @objid ("f30c960c-5b49-4713-9669-a425bb519d74")
    private IGmObject getSelectedSymbol() {
        return this.selectedSymbol;
    }

    @objid ("87858bc6-353a-4a8a-a4c2-8bdf4c28b763")
    StyleEditPanel getStyleEditPanel() {
        return this.styleEditPanel;
    }

    @objid ("bd58280a-9d6f-4aa1-b2c4-b8456baf30eb")
    private void updateToolbar() {
        this.toolbarContribs.refresh();
        this.toolBarManager.update(true);
        this.top.layout(true, true);
    }

    @objid ("8c2425de-9229-440e-906a-b3ee4d43acae")
    private final class SymbolModel implements ISymbolPanelModel {
        @objid ("f2fa5818-789d-4206-9efa-939eb07d4502")
        @Override
        public IStyle getStyleInput() {
            StyleEditPanelUIData d = getData();
            if (d != null) {
                return d.getStyleData();
            }
            return null;
        }

        @objid ("beca9359-7416-4d83-a2f5-1482b5915ef3")
        @Override
        public IGmObject getSelectedSymbol() {
            return SymbolPanelProvider.this.getSelectedSymbol();
        }

        @objid ("c1280349-f936-4bbd-9772-89d63437f41e")
        @Override
        public StyleEditPanelSelection getPanelSelection() {
            return getStyleEditPanel().getSelection();
        }

        @objid ("f292f50f-1c1b-406a-81cf-38a8b40399ab")
        @Override
        public boolean isShowHelp() {
            return SymbolPanelProvider.this.isShowHelp();
        }

        @objid ("b78a0f27-5bf1-4555-a633-dc06f54bb3d2")
        @Override
        public Shell getSwtShell() {
            return getPanel().getShell();
        }

    }

    @objid ("07ad0534-a7a6-43ca-85cc-e4bf8510faa2")
    private final class SymbolController implements ISymbolPanelController {
        @objid ("c597c84c-2ae7-4cd6-a8ae-98b5a8dc7fa6")
        @Override
        public void onShowHelp() {
            SymbolPanelProvider.this.showHelp = !SymbolPanelProvider.this.showHelp;
            getStyleEditPanel().showHelpPanel(SymbolPanelProvider.this.showHelp);
        }

        @objid ("a9944eab-3fa6-49d8-8d16-319a58cab484")
        @Override
        public void refreshView() {
            getStyleEditPanel().getViewer().refresh();
        }

    }

}

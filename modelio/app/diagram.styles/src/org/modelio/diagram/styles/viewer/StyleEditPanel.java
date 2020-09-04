/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.styles.viewer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.ui.panel.IPanelListener;
import org.modelio.ui.panel.IPanelProvider;

@objid ("260d58ae-7a2c-4eb5-91b3-4e26f75323f4")
public class StyleEditPanel implements IPanelProvider {
    @objid ("7b01722b-fba1-4985-b599-2bce07f37a82")
    private final boolean tableMode;

    @objid ("75e4d43e-8e6b-4aa2-bcae-15dbd39913de")
    private final StyleEditPanelController controller = new StyleEditPanelController();

    @objid ("7b729eb6-f26a-49d2-a175-4b29991285f8")
    @Override
    public boolean isRelevantFor(Object obj) {
        return false;
    }

    @objid ("5afe99bf-073c-426e-a683-920f1bc11fd1")
    @Override
    public Object createPanel(Composite parent) {
        return this.controller.createUi(parent, this.tableMode);
    }

    @objid ("70acc363-8355-41e4-bae9-8c021cc31803")
    @Override
    public Composite getPanel() {
        return this.controller.getUi().getSash();
    }

    @objid ("daed1f57-bfdb-4908-a52c-4a13944a2760")
    @Override
    public String getHelpTopic() {
        return DiagramStyles.I18N.getString("StyleEditPanel.helptopic");
    }

    @objid ("a609b337-5df8-4908-b0b1-9b933c4578dd")
    @Override
    public StyleEditPanelUIData getInput() {
        return this.controller.getData();
    }

    /**
     * A {@link StyleEditPanelUIData} is expected as input.
     */
    @objid ("90f15233-16ba-46ba-bc91-309f2edb5027")
    @Override
    public void setInput(Object input) {
        if (input instanceof StyleEditPanelUIData) {
            this.controller.setData((StyleEditPanelUIData) input);
        } else {
            this.controller.setData(null);
        }
    }

    @objid ("621052e7-47e1-4110-8ae7-b0418866ee39")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("92be71c5-225a-4780-ac5a-0f87549c26e9")
    @Override
    public void addListener(IPanelListener l) {
        this.controller.addListener(l);
    }

    @objid ("9d12d90c-1ede-49f4-9d40-5c17540a8138")
    @Override
    public void removeListener(IPanelListener l) {
        this.controller.removeListener(l);
    }

    /**
     * Return the edition viewer.
     * <p>
     * It may be either a TreeViewer or a TableViewer.
     * 
     * @return the style edition viewer.
     */
    @objid ("c7b10464-6f5d-41ec-81b9-f041ff5bc1fc")
    public ColumnViewer getViewer() {
        return this.controller.getUi().viewer;
    }

    @objid ("9bd23e26-401d-4314-8580-88950fdbc733")
    public void showHelpPanel(boolean showHelp) {
        this.controller.showHelp(showHelp);
    }

    /**
     * Private constructor.
     * <p>
     * Call either {@link #newTablePanel()} or {@link #newTreePanel()} .
     * 
     * @param tableMode true for a table panel, false for a tree panel.
     */
    @objid ("cc54edbc-1d5f-4f95-a867-be705ffdcf64")
    private StyleEditPanel(boolean tableMode) {
        this.tableMode = tableMode;
    }

    /**
     * Creates a table style edit panel.
     * 
     * @return the style edit panel.
     */
    @objid ("9e5489d3-57d2-4912-b581-82eb129b7997")
    public static StyleEditPanel newTablePanel() {
        return new StyleEditPanel(true);
    }

    /**
     * Creates a tree style edit panel.
     * 
     * @return the style edit panel.
     */
    @objid ("0577556d-5c78-4e0a-aab9-5cf61d35f1e8")
    public static StyleEditPanel newTreePanel() {
        return new StyleEditPanel(false);
    }

    /**
     * Get the contextual menu manager.
     * <p>
     * The caller may add entries to the menu and register listeners.
     * 
     * @return the contextual menu manager.
     */
    @objid ("7c581986-d984-4a72-be20-0f8f0b93d1ed")
    public MenuManager getContextualMenu() {
        return this.controller.getUi().getContextualMenu();
    }

    /**
     * Get lazily computed expressions about the current selection.
     * <p>
     * Use them to set visibility of contextual menu items dynamically.
     * 
     * @return selected elements computations.
     */
    @objid ("91b8ce00-e5c6-429e-b166-73acd0547b1d")
    public StyleEditPanelSelection getSelection() {
        return this.controller.getUi().getSelectionComputations();
    }

}

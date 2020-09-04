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

package org.modelio.diagram.symbol.panel;

import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.contribitem.SwtContributionItem.Style;
import org.modelio.core.ui.swt.contribitem.SwtContributionItem;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.style.StyleEditorProxy;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.viewer.StyleEditPanelSelection;
import org.modelio.diagram.symbol.plugin.DiagramSymbol;
import org.modelio.utils.i18n.BundledMessages;

/**
 * Factory that builds contextual menu and toolbar content.
 * <p>
 * The caller may then add instantiated contribution items individually to a menu manager or a toolbar manager.
 * <p>
 * The instantiated contribution items may be added to only one manager, you have to instantiate a factory for each menu and toolbar .
 * 
 * @author cma
 * @since 3.7
 */
@objid ("5ea55cf5-6c69-4ec4-8ecd-f276c029a6df")
class SymbolContributionFactory {
    @objid ("956d9c46-42a4-4d7c-874c-bf46ce5a69dd")
    private ISymbolPanelController symbolController;

    @objid ("35b8a3c9-07c5-47cc-894b-03e2e6cc65ef")
    public final SwtContributionItem extractStyleFromModified;

    @objid ("5861b105-fde5-4003-902a-c10a7c46cf33")
    public final SwtContributionItem extractStyleFromSelected;

    @objid ("f3a2a415-3366-486c-8ef7-6183a090a3e4")
    public final SwtContributionItem reset;

    @objid ("1c2af511-0c44-4903-b82c-6e1d789dcc6b")
    public final SwtContributionItem showHelp;

    @objid ("0b90eeae-d832-4a19-90b3-5b55d6309e53")
    public final SwtContributionItem updateStyleFromAllModified;

    @objid ("5ca6f3a4-7123-4e88-ab0b-f98775012b81")
    public final SwtContributionItem updateStyleFromSelected;

    @objid ("26a12dec-6e19-4693-bfbc-a8fb8f567da3")
    private final ISymbolPanelModel symbolModel;

    @objid ("128d38ef-e4df-44fc-8f1c-822138f1dcfd")
    public SymbolContributionFactory(ISymbolPanelController controller, ISymbolPanelModel symbolModel) {
        this.symbolController = controller;
        this.symbolModel = symbolModel;
        
        this.extractStyleFromSelected = new SwtContributionItem();
        this.extractStyleFromModified = new SwtContributionItem();
        this.updateStyleFromSelected = new SwtContributionItem();
        this.updateStyleFromAllModified = new SwtContributionItem();
        this.reset = new SwtContributionItem();
        this.showHelp = new SwtContributionItem(Style.AS_CHECK_BOX);
        
        this.extractStyleFromSelected.setAction(() -> onExtractStyle(false));
        this.extractStyleFromModified.setAction(() -> onExtractStyle(true));
        this.updateStyleFromSelected.setAction(this::onUpdateStyleFromSelectedProps);
        this.updateStyleFromAllModified.setAction(this::onUpdateStyleFromModifiedProps);
        
        this.reset.setText(DiagramSymbol.I18N.getMessage("Reset.label"));
        this.reset.setTooltipText(DiagramSymbol.I18N.getMessage("Reset.tooltip"));
        this.reset.setImageDescriptor(getImage(DiagramSymbol.I18N.getMessage("Reset.image")));
        this.reset.setAction(this::onResetStyle);
        
        this.showHelp.setText(DiagramSymbol.I18N.getMessage("ShowSymbolHelp.label"));
        this.showHelp.setTooltipText(DiagramSymbol.I18N.getMessage("ShowSymbolHelp.tooltip"));
        this.showHelp.setImageDescriptor(getImage(DiagramSymbol.I18N.getMessage("ShowSymbolHelp.image")));
        this.showHelp.setChecked(symbolModel.isShowHelp());
        this.showHelp.setAction(() -> {
            controller.onShowHelp();
            this.showHelp.setChecked(symbolModel.isShowHelp());
            this.showHelp.update();
        });
    }

    /**
     * To be called when the view selection or content changes
     */
    @objid ("ec668dbe-5211-4b26-9a18-130216a4f895")
    public void refresh() {
        final StyleEditPanelSelection panelSelection = this.symbolModel.getPanelSelection();
        final IStyle editedStyle = this.symbolModel.getStyleInput();
        
        if (editedStyle == null || panelSelection == null) {
            this.extractStyleFromSelected.setVisible(false);
            this.updateStyleFromSelected.setVisible(false);
        
            this.extractStyleFromModified.setVisible(false);
            this.updateStyleFromAllModified.setVisible(false);
            return;
        }
        
        final ISelection sel = panelSelection.getSelection();
        final boolean containsModifiedProperties = panelSelection.containsModifiedProperties();
        
        final BundledMessages i18n = DiagramSymbol.I18N;
        final int nbModified = editedStyle.getLocalKeys().size();
        final NamedStyle parentStyle = getNamedStyle(editedStyle);
        final String parentStyleName = parentStyle.getName();
        final String escParentStyleName = escapeMnemonics(parentStyleName);
        final String styleOrTheme = parentStyle.isTheme() ? i18n.getMessage("SymbolPanelProvider.isTheme") : i18n.getMessage("SymbolPanelProvider.isStyle");
        final boolean shouldCreateTheme = this.symbolModel.shouldCreateTheme();
        final String createStyleOrTheme = shouldCreateTheme ? i18n.getMessage("SymbolPanelProvider.isTheme") : i18n.getMessage("SymbolPanelProvider.isStyle");
        
        if (containsModifiedProperties) {
            ISymbolViewItem firstProp = SelectionHelper.getFirst(sel, ISymbolViewItem.class);
            String firstLabel = firstProp.getLabel();
            String escFirstLabel = escapeMnemonics(firstLabel);
            String allLabels = SelectionHelper.toStream(sel, ISymbolViewItem.class).map(i -> " - " + i.getLabel()).collect(Collectors.joining("\n"));
        
            int size = SelectionHelper.size(sel);
            this.extractStyleFromSelected.setText(i18n.getMessage("SymbolPanelProvider.ExtractStyleFromSelectedCommand.label", size, escFirstLabel, escParentStyleName, styleOrTheme, allLabels, createStyleOrTheme));
            this.extractStyleFromSelected.setTooltipText(i18n.getMessage("SymbolPanelProvider.ExtractStyleFromSelectedCommand.tooltip", size, firstLabel, parentStyleName, styleOrTheme, allLabels, createStyleOrTheme));
        
            this.updateStyleFromSelected.setText(i18n.getMessage("SymbolPanelProvider.UpdateStyleFromSelectedCommand.label", size, escFirstLabel, escParentStyleName, styleOrTheme, allLabels));
            this.updateStyleFromSelected.setTooltipText(i18n.getMessage("SymbolPanelProvider.UpdateStyleFromSelectedCommand.tooltip", size, firstLabel, parentStyleName, styleOrTheme, allLabels));
        }
        
        if (nbModified > 0) {
            String firstLabel = editedStyle.getLocalKeys().iterator().next().getLabel();
            String escFirstLabel = escapeMnemonics(firstLabel);
            String allLabels = editedStyle.getLocalKeys().stream().map(i -> " - " + i.getLabel()).collect(Collectors.joining("\n"));
            this.extractStyleFromModified.setText(i18n.getMessage("SymbolPanelProvider.ExtractStyleFromModifiedCommand.label", nbModified, escFirstLabel, allLabels, createStyleOrTheme));
            this.extractStyleFromModified.setTooltipText(i18n.getMessage("SymbolPanelProvider.ExtractStyleFromModifiedCommand.tooltip", nbModified, firstLabel, parentStyleName, styleOrTheme, allLabels, createStyleOrTheme));
        
            this.updateStyleFromAllModified.setText(i18n.getMessage("SymbolPanelProvider.UpdateStyleFromModifiedCommand.label", nbModified, escFirstLabel, escParentStyleName, styleOrTheme, allLabels));
            this.updateStyleFromAllModified.setTooltipText(i18n.getMessage("SymbolPanelProvider.UpdateStyleFromModifiedCommand.tooltip", nbModified, firstLabel, parentStyleName, styleOrTheme, allLabels));
        }
        
        ImageDescriptor createImage = getImage(shouldCreateTheme ? DiagramSymbol.I18N.getMessage("SymbolPanelProvider.create.theme.image") : DiagramSymbol.I18N.getMessage("SymbolPanelProvider.create.style.image"));
        this.extractStyleFromSelected.setImageDescriptor(createImage);
        this.extractStyleFromModified.setImageDescriptor(createImage);
        
        ImageDescriptor updateImage = getImage(parentStyle.isTheme() ? DiagramSymbol.I18N.getMessage("SymbolPanelProvider.update.theme.image") : DiagramSymbol.I18N.getMessage("SymbolPanelProvider.update.style.image"));
        this.updateStyleFromSelected.setImageDescriptor(updateImage);
        this.updateStyleFromAllModified.setImageDescriptor(updateImage);
        
        this.extractStyleFromModified.setVisible(nbModified > 0);
        this.updateStyleFromAllModified.setVisible(nbModified > 0);
        
        this.extractStyleFromSelected.setVisible(containsModifiedProperties);
        this.updateStyleFromSelected.setVisible(containsModifiedProperties);
        
        this.showHelp.setChecked(this.symbolModel.isShowHelp());
        
        this.extractStyleFromModified.update();
        this.extractStyleFromSelected.update();
        this.updateStyleFromAllModified.update();
        this.updateStyleFromSelected.update();
        this.showHelp.update();
    }

    /**
     * Escape mnemonics from the given string
     * 
     * @param s a string
     * @return a string where all '&' are escaped to '&&'.
     */
    @objid ("6e4cfab8-847b-4460-b729-a8d86b3b234b")
    private static String escapeMnemonics(String s) {
        return LegacyActionTools.escapeMnemonics(s);
    }

    @objid ("206272d4-a63c-4ee6-9ccd-5a379131275d")
    private static ImageDescriptor getImage(String path) {
        return DiagramSymbol.getImageDescriptor(path);
    }

    @objid ("1a4765f0-1b2f-48a8-bc41-181e50094c79")
    private static NamedStyle getNamedStyle(IStyle s) {
        for (IStyle p = s; p != null; p = p.getCascadedStyle()) {
            if (p instanceof NamedStyle) {
                return (NamedStyle) p;
            }
        }
        throw new IllegalArgumentException(String.format("%s style has no named style in its parent hierarchy.", s));
    }

    /**
     * Update the named style from all modified style keys.
     */
    @objid ("725e2ef8-f8e3-45f9-860e-72f181a36a31")
    private void onUpdateStyleFromModifiedProps() {
        new UpdateStyleFromModifiedPropsCommand(this.symbolModel).execute();
        
        this.symbolController.refreshView();
    }

    /**
     * Update the named style from the selected style keys.
     */
    @objid ("a22c948e-7238-46a1-a554-52a00a75e743")
    private void onUpdateStyleFromSelectedProps() {
        new UpdateStyleFromSelectedPropsCommand(this.symbolModel).execute();
        
        this.symbolController.refreshView();
    }

    @objid ("a6997258-b273-4d15-a17d-976760dc71c2")
    private void onResetStyle() {
        IGmObject gm = this.symbolModel.getSelectedSymbol();
        if (gm != null) {
            new StyleEditorProxy(gm).reset();
        }
    }

    /**
     * Command that extract a style from modified style keys
     * 
     * @param allLocals if true, use all modified style keys, if false use only selected style keys.
     */
    @objid ("74c042c5-7415-4b78-8ad0-f36038ed1a14")
    private void onExtractStyle(boolean allLocals) {
        new ExtractStyleCommand(this.symbolModel).execute(allLocals);
        
        this.symbolController.refreshView();
    }

}

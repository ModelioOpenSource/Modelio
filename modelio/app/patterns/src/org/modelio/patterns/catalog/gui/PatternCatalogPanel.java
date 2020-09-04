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

package org.modelio.patterns.catalog.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.JAXBException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.api.IPatternRepository;
import org.modelio.patterns.model.CategoryData;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.model.information.Category;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.ui.UIColor;
import org.modelio.ui.UIImages;
import org.modelio.ui.panel.IPanelListener;
import org.modelio.ui.panel.IPanelProvider;

@objid ("afe14067-60cc-465f-9c55-fd945d019797")
public class PatternCatalogPanel implements IPanelProvider {
    @objid ("d4828523-b981-4a96-b148-b2ed9adfff83")
    private PatternCatalogController controller;

    @objid ("7ec5586c-b175-4a07-8f9b-cdbd12a0d815")
    @Override
    public boolean isRelevantFor(Object obj) {
        
        return obj instanceof ModelElement;
    }

    @objid ("8e068d9a-2111-4c3c-a7f3-8223dc757056")
    @Override
    public Control createPanel(Composite parent) {
        
        return this.controller.createUi(parent);
    }

    @objid ("912d1d4b-601d-4b13-a879-b29d9ee68053")
    @Override
    public Control getPanel() {
        
        return this.controller.getUi();
    }

    @objid ("17d59dc6-0aae-4998-b03e-6ff7021d710e")
    @Override
    public String getHelpTopic() {
        
        return Patterns.I18N.getString("PatternCatalogPanel.helptopic");
    }

    @objid ("016bdfbb-765e-4c4e-a805-74cb392fe222")
    @Override
    public Object getInput() {
        
        return this.controller.getData();
    }

    @objid ("f4819fe5-a0b1-4ebd-ba23-b771aa6ec6a1")
    @Override
    public void setInput(Object input) {
        if (input instanceof PatternCatalogData) {
            this.controller.setData((PatternCatalogData) input);
        } else {
            this.controller.setData(null);
        }
    }

    @objid ("a10c86a1-4e98-43ed-a8a3-1c58dea88548")
    @Override
    public void addListener(IPanelListener l) {
        this.controller.addListener(l);
    }

    @objid ("beeb64c3-63e6-4b32-a43e-7fdbd2f18978")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("b1946d6d-a630-488a-91f5-9f3b40c0b05e")
    @Override
    public void removeListener(IPanelListener l) {
        this.controller.removeListener(l);
    }

    @objid ("d5434e9e-9974-4135-be31-a4f55749fbc2")
    public PatternCatalogPanel() {
        this.controller = new PatternCatalogController();
    }

    @objid ("9f33e762-0485-4f42-b5bd-682afe899eb5")
    private static class PatternCatalogPanelUI {
        @objid ("73d84ea9-e1eb-4029-bf30-1fc96df58dd5")
        private Composite composite = null;

        @objid ("f7007d0a-8a4a-47d0-8b49-aa0d177782f4")
        private PatternCatalogController controller;

        @objid ("abef6dbe-7c22-46c7-aedf-d74dd9078903")
        private TreeViewer patternTree;

        @objid ("ab576594-d398-4267-a726-f982924181ec")
        private Composite idPanel;

        @objid ("8e5f1f32-f542-4520-aa8d-2f8b02aed5af")
        private Text nameText;

        @objid ("ac58fd65-05b7-4804-a0f2-46fab9a4133d")
        private Text versionText;

        @objid ("c9202ded-932d-4212-bf6f-5125baab82d0")
        private Text descriptionText;

        @objid ("2378c86a-6f25-447f-bc19-7e77523a38b9")
        private Button deleteButton;

        @objid ("042ed866-3258-43c9-ba30-0572a33f9f14")
        private Button installButton;

        @objid ("81561aab-944e-48f3-a15d-294fbccd7c13")
        public PatternCatalogPanelUI(PatternCatalogController controller) {
            this.controller = controller;
        }

        @objid ("f3c052b4-92c7-49aa-9363-b5859952e9a4")
        public Control createUI(Composite parent) {
            this.composite = new Composite(parent, SWT.NONE);
            this.composite.setLayoutData(new GridData(GridData.FILL_BOTH));
            
            GridLayout layout = new GridLayout(3, false);
            layout.marginHeight = 5;
            layout.marginWidth = 5;
            layout.verticalSpacing = 5;
            layout.horizontalSpacing = 0;
            this.composite.setLayout(layout);
            
            this.patternTree = createPatternTree(this.composite);
            this.patternTree.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            
            Composite toolbar = new Composite(this.composite, SWT.NONE);
            toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
            toolbar.setLayout(new RowLayout(SWT.VERTICAL));
            
            this.idPanel = createIdentificationPanel(this.composite);
            this.idPanel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
            
            this.deleteButton = new Button(toolbar, SWT.PUSH);
            this.deleteButton.setImage(UIImages.DELETE);
            this.deleteButton.setToolTipText(Patterns.I18N.getString("PatternCatalogPanel.DeletePattern.button"));
            this.deleteButton.addSelectionListener(new SelectionListener() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    PatternCatalogPanelUI.this.controller.onDeletePattern(SelectionHelper.getFirst(PatternCatalogPanelUI.this.patternTree.getSelection(), RuntimePattern.class));
                }
            
                @Override
                public void widgetDefaultSelected(SelectionEvent e) {
                    // Nothing to do
                }
            });
            
            this.installButton = new Button(this.composite, SWT.PUSH);
            this.installButton.setText(Patterns.I18N.getString("PatternCatalogPanel.InstallPattern.button.label"));
            this.installButton.setToolTipText(Patterns.I18N.getString("PatternCatalogPanel.InstallPattern.button.tooltip"));
            this.installButton.addSelectionListener(new SelectionListener() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    PatternCatalogPanelUI.this.controller.onInstallPattern();
                }
            
                @Override
                public void widgetDefaultSelected(SelectionEvent e) {
                    // Nothing to do
                }
            });
            return this.composite;
        }

        @objid ("5d327a67-4e80-47b2-9cd4-5daa6856287b")
        public void update(PatternCatalogData data) {
            if (data != null) {
                // Dispatch parameters between the two tables
                Object[] oldExpanded = this.patternTree.getExpandedElements();
                this.patternTree.setInput(data.getRepository());
                this.patternTree.setExpandedElements(oldExpanded);
            
                RuntimePattern selectedPattern = data.getSelectedPattern();
                if (selectedPattern != null) {
                    this.idPanel.setVisible(true);
            
                    this.nameText.setText(selectedPattern.getName());
                    this.versionText.setText(selectedPattern.getVersion());
                    this.descriptionText.setText(selectedPattern.getDescription());
                    //this.iconPreviewLabel.setImage(selectedPattern.getIcon());
            
                    this.deleteButton.setEnabled(true);
                } else {
                    this.idPanel.setVisible(false);
            
                    this.deleteButton.setEnabled(false);
                }
            } else {
                this.patternTree.setInput(Collections.emptyList());
            }
        }

        @objid ("bc1f4a17-710e-4f88-8d63-2817bc97517b")
        public void dispose() {
            this.composite.dispose();
        }

        @objid ("33a0ba1e-8f51-4669-80c0-69f0c0b01899")
        private TreeViewer createPatternTree(Composite parent) {
            TreeViewer patternTree = new TreeViewer(parent, SWT.BORDER);
            patternTree.setContentProvider(new ITreeContentProvider() {
                @Override
                public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                    // Nothing to do
                }
            
                @Override
                public void dispose() {
                    // Nothing to dispose
                }
            
                @Override
                public boolean hasChildren(Object element) {
                    if (element instanceof CategoryData) {
                        return ! ((CategoryData) element).getPatterns().isEmpty();
                    }
                    return false;
                }
            
                @Override
                public Object getParent(Object element) {
                    if (element instanceof RuntimePattern) {
                        List<Category> categories = ((RuntimePattern) element).getCategories();
                        return categories.isEmpty() ? null : categories.get(categories.size() - 1);
                    } else if (element instanceof CategoryData) {
                        return PatternCatalogPanelUI.this.controller.data.getRepository();
                    }
                    return null;
                }
            
                @Override
                public Object[] getElements(Object inputElement) {
                    if (inputElement instanceof IPatternRepository) {
                        return ((IPatternRepository) inputElement).getCategories().toArray();
                    }
                    return Collections.emptyList().toArray();
                }
            
                @Override
                public Object[] getChildren(Object parentElement) {
                    if (parentElement instanceof CategoryData) {
                        return ((CategoryData) parentElement).getPatterns().toArray();
                    }
                    return Collections.emptyList().toArray();
                }
            });
            patternTree.setLabelProvider(new LabelProvider() {
                @Override
                public String getText(Object element) {
                    if (element instanceof CategoryData) {
                        return ((CategoryData) element).getName();
                    } else if (element instanceof RuntimePattern) {
                        return ((RuntimePattern) element).getName();
                    }
                    return super.getText(element);
                }
            
                @Override
                public Image getImage(Object element) {
                    if (element instanceof CategoryData) {
                        return ((CategoryData) element).getIcon();
                    } else if (element instanceof RuntimePattern) {
                        return ((RuntimePattern) element).getIcon();
                    }
                    return super.getImage(element);
                }
            });
            patternTree.addSelectionChangedListener(new ISelectionChangedListener() {
            
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    PatternCatalogPanelUI.this.controller.onPatternChange(SelectionHelper.getFirst(event.getSelection(), RuntimePattern.class));
                }
            });
            return patternTree;
        }

        @objid ("64e71939-bad4-47d6-9b11-456cabf32425")
        private Composite createIdentificationPanel(Composite parent) {
            Group contents = new Group(parent, SWT.NONE);
            contents.setLayout(new GridLayout(2, false));
            contents.setText(Patterns.I18N.getString("PatternCatalogPanel.Group.label"));
            
            Label nameLabel = new Label(contents, SWT.NONE);
            nameLabel.setText(Patterns.I18N.getString("PatternCatalogPanel.Name.label"));
            nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            
            this.nameText = new Text(contents, SWT.BORDER);
            this.nameText.setEnabled(false);
            this.nameText.setBackground(UIColor.TEXT_READONLY_BG);
            this.nameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            this.nameText.setToolTipText(Patterns.I18N.getString("PatternCatalogPanel.Name.tooltip"));
            
            Label versionLabel = new Label(contents, SWT.NONE);
            versionLabel.setText(Patterns.I18N.getString("PatternCatalogPanel.Version.label"));
            versionLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
            
            this.versionText = new Text(contents, SWT.BORDER);
            this.versionText.setEnabled(false);
            this.versionText.setBackground(UIColor.TEXT_READONLY_BG);
            this.versionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            this.versionText.setToolTipText(Patterns.I18N.getString("PatternCatalogPanel.Version.tooltip"));
            
            //Label iconLabel = new Label(contents, SWT.NONE);
            //iconLabel.setText(Patterns.I18N.getString("PatternCatalogPanel.Icon.label"));
            //iconLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
            
            //this.iconPreviewLabel = new Label(contents, SWT.NONE);
            
            Label descriptionLabel = new Label(contents, SWT.NONE);
            descriptionLabel.setText(Patterns.I18N.getString("PatternCatalogPanel.Description.label"));
            descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
            
            this.descriptionText = new Text(contents, SWT.BORDER | SWT.MULTI);
            this.descriptionText.setEnabled(false);
            this.descriptionText.setBackground(UIColor.TEXT_READONLY_BG);
            this.descriptionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.descriptionText.setToolTipText(Patterns.I18N.getString("PatternCatalogPanel.Description.tooltip"));
            return contents;
        }

        @objid ("af731c67-3b30-4035-8f8a-8b0dc039867e")
        public Path selectPattern() {
            FileDialog dialog = new FileDialog(this.composite.getShell(), SWT.OPEN);
            
            String[] extensions = { "*.umlt" }; //$NON-NLS-1$
            dialog.setFilterExtensions(extensions);
            dialog.setFilterNames(extensions);
            String selectFilename = dialog.open();
            
            if (selectFilename != null) {
                return Paths.get(selectFilename);
            }
            return null;
        }

    }

    @objid ("c1c16a7c-c7aa-439c-8750-9cbc6623e3cb")
    private static class PatternCatalogController {
        @objid ("dd634716-98dc-4eb9-993a-6e4b7fd8f294")
        private PatternCatalogPanelUI ui;

        @objid ("22771bd4-bf78-4a16-9f81-b90a3196dfb9")
        private List<IPanelListener> listeners = new ArrayList<>();

        @objid ("b5c51d1b-15ec-462a-88d7-526a15ed84d9")
        private PatternCatalogData data;

        @objid ("f2470e28-23eb-4af6-a518-d2b9f64883df")
        public PatternCatalogData getData() {
            
            return this.data;
        }

        @objid ("1a38ec7e-58f2-4897-aa36-e32d1cff8277")
        public void setData(PatternCatalogData data) {
            this.data = data;
            if (this.ui != null) {
                this.ui.update(this.data);
            }
        }

        @objid ("c36027ef-77eb-412c-9d71-b42f26d51b10")
        public Control createUi(Composite parent) {
            this.ui = new PatternCatalogPanelUI(this);
            Control control = this.ui.createUI(parent);
            this.ui.update(this.data);
            return control;
        }

        @objid ("f74f72ee-8d8c-421a-8b05-63cab7cf996b")
        public Control getUi() {
            
            return this.ui.composite;
        }

        @objid ("36aa1101-5718-4a9c-a721-86e206fbe58b")
        public void dispose() {
            this.ui.dispose();
            this.ui = null;
        }

        @objid ("cb65c187-c9e0-4283-ac8c-d7e908bf49a9")
        private void fireListeners(PatternCatalogData data, boolean isValidate) {
            this.listeners.forEach(l -> l.dataChanged(data, isValidate));
        }

        @objid ("ea40ee19-b75a-4c2a-a116-fa1b007a4e75")
        public synchronized void removeListener(IPanelListener l) {
            this.listeners.remove(l);
        }

        @objid ("401e4506-df4b-4425-ae8a-c3d5afdb9cfc")
        public synchronized void addListener(IPanelListener l) {
            if (this.listeners.contains(l)) {
                throw new InvalidParameterException("Listener already registered");
            }
            this.listeners.add(l);
        }

        @objid ("b1f81060-f77a-4f52-99c9-9e979793508a")
        public void onPatternChange(RuntimePattern newValue) {
            Object oldValue = this.data.getSelectedPattern();
            if (Objects.equals(oldValue, newValue) == false) {
                this.data.setSelectedPattern(newValue);
                this.ui.update(this.data);
                fireListeners(this.data, true);
            }
        }

        @objid ("6286c769-667f-4036-bef4-1ae9bcab1bd5")
        public void onDeletePattern(RuntimePattern value) {
            if (value != null) {
                this.data.getRepository().removePattern(value);
                this.data.getRepository().reloadPatterns();
                this.data.setSelectedPattern(null);
                this.ui.update(this.data);
                fireListeners(this.data, true);
            }
        }

        @objid ("976b3230-d3f1-4571-acb6-c2ad8ad81f06")
        public void onInstallPattern() {
            Path patternPath = this.ui.selectPattern();
            if (patternPath != null && Files.exists(patternPath)) {
                try {
                    this.data.getRepository().addPattern(patternPath);
                } catch (IOException | JAXBException e) {
                    MessageDialog.openError(this.ui.composite.getShell(), Patterns.I18N.getString("Gui.ErrorTitle"), e.getMessage());
                }
                this.data.getRepository().reloadPatterns();
                this.data.setSelectedPattern(null);
                this.ui.update(this.data);
                fireListeners(this.data, true);
            }
        }

    }

}

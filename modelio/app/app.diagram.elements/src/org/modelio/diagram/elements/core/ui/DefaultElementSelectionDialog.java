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
package org.modelio.diagram.elements.core.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog2;
import org.modelio.platform.ui.dialog.PolluxFieldBuilder;
import org.modelio.platform.ui.dialog.PolluxWidgetConfigurator;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("cad13b8b-97b9-4c6f-9ac7-f37911b660ec")
public class DefaultElementSelectionDialog extends ModelioDialog2 {
    @objid ("ebf369a0-5f00-427f-aba1-8bc5e8b4271c")
    private ElementSelectionData data;

    @objid ("d3795adb-e690-4c8e-9d3f-07a0c757c18e")
    private ElementToUnmaskFinder elementFinder;

    @objid ("d355bf6a-a891-4f76-888e-9112003d5763")
    private Controler controler;

    @objid ("2e8bae98-56ac-4c1e-8a10-87ca814c09e6")
    private Ui ui;

    @objid ("a01e681a-0cd9-4320-b211-52e2655daaa9")
    private ICoreSession session;

    @objid ("08038e80-fa49-4314-a142-f66c3e831044")
    private static IModelioNavigationService navigationService;

    @objid ("75d42acf-be4a-4267-9e2b-73ad4e82751b")
    public  DefaultElementSelectionDialog(Shell parentShell, ICoreSession session, IModelioNavigationService navigationService) {
        super(parentShell);
        this.controler = new Controler();
        this.ui = new Ui(this.controler);
        this.controler.setUi(this.ui);
        this.session = session;
        DefaultElementSelectionDialog.navigationService = navigationService;
        
    }

    @objid ("44d0886c-d3f9-4332-99df-d307ec7cb673")
    @Override
    protected Point getInitialSize() {
        return new Point(615, 400);
    }

    @objid ("13e4c77e-ca0c-4337-806c-dc7601042709")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        addDefaultButtons(parent);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
        this.ui.setOkButton(getButton(IDialogConstants.OK_ID));
        
    }

    @objid ("91dd4985-7fba-434a-b9b5-5bc2cd9378a5")
    @Override
    protected Control createContentArea(Composite parent) {
        Composite top = this.ui.create(parent);
        if (this.data != null) {
            this.controler.setData(data);
        }
        return top;
    }

    @objid ("92d000fe-0820-4668-8efd-62cc7c764af1")
    @Override
    protected void init() {
        
    }

    @objid ("08f6fb55-a648-4ffe-b635-00778de93939")
    public void setInput(ElementSelectionData data) {
        Display display = Display.getCurrent();
        this.data = data;
        this.elementFinder = new ElementToUnmaskFinder(this.data.getMetaclass(), this.session);
        if (this.data.getDiagramFilter() != null) {
            this.elementFinder.setDiagramFilter(this.data.getDiagramFilter());
        }
        
        if (this.data.getContextFilter() != null) {
            this.elementFinder.setContextFilter(this.data.getContextFilter());
        }
        this.elementFinder.asyncSearch(s -> this.controler.updateData(s, display));
        
    }

    @objid ("4ca3e479-1119-448b-848f-7870cc0b9358")
    private static class Controler {
        @objid ("f53bb2ac-1fd8-41ce-9d53-013cb4b9e746")
        private Ui ui;

        @objid ("c5227880-4bfa-4c91-aaa7-9aff06fc896b")
        private ElementSelectionData data;

        @objid ("e93d4bc8-b9d0-4484-b72b-8b25094b6bbb")
        public void setUi(Ui ui) {
            this.ui = ui;
        }

        @objid ("2adb3977-3609-43b8-a3fe-63472d86a04e")
        public void setData(ElementSelectionData data) {
            this.data = data;
            this.ui.update(this.data);
            
        }

        @objid ("f149555f-14d7-419c-8850-62c36026176e")
        public void updateData(List<MObject> candidates, Display display) {
            display.syncExec(new Runnable() {
                @Override
                public void run() {
                    data.setCandidates(candidates);
                    ui.update(data);
                }
            });
            
        }

        @objid ("600569e6-3823-46f6-a539-ea2229444875")
        public void onElementSelectionChange(Object element) {
            if (element != null && !element.equals(this.data.getSelectedElement())) {
                this.data.setSelectedElement(element);
                this.ui.update(this.data);
                this.ui.nameText.setFocus();
                if (DefaultElementSelectionDialog.navigationService != null) {
                    DefaultElementSelectionDialog.navigationService.fireNavigate((MObject) element);
                }
            }
            
        }

        @objid ("ceca98de-496b-4128-8efa-56b1128e3bf4")
        public void onNameChange(String name) {
            if (this.data.getSelectedElement() instanceof ElementPlaceolderData) {
                ((ElementPlaceolderData) this.data.getSelectedElement()).setName(name);
                if (this.ui.okButton != null) {
                    this.ui.okButton.setEnabled(!"".equals(name));
                }
            }
            
        }

        @objid ("5dd96888-036c-44ff-abb0-b9e6a2e49040")
        public void onDescriptionChange(String description) {
            if (this.data.getSelectedElement() instanceof ElementPlaceolderData) {
                ((ElementPlaceolderData) this.data.getSelectedElement()).setDescription(description);
            }
            
        }

        @objid ("7d2749f8-f4e8-4ba8-b5d5-2773d64af8b6")
        public void onSerachUpdate(String searchText) {
            List<Object> filtre = new ArrayList<>();
            for (Object candidate : this.data.getCandidates()) {
                if (candidate instanceof MObject) {
                    MObject mobj = (MObject) candidate;
                    if (mobj.getName().contains(searchText)) {
                        filtre.add(candidate);
                    }
                }
            }
            
            filtre.add(0, this.data.getCandidates().iterator().next());
            this.data.setFiltredCandidates(filtre);
            this.ui.update(data);
            
        }

    }

    @objid ("1ef7eed1-8d7b-48d4-8674-9e856a7ed518")
    private static class Ui {
        @objid ("34c17f2b-04b3-4d5f-9a75-bc00c33bdea7")
        private Controler controler;

        @objid ("c0492d48-e4d8-434a-99af-605acac42bf4")
        private Composite rootComposite;

        @objid ("70b5db19-5f7d-43f8-b672-7583419ae166")
        private CLabel headerLabel;

        @objid ("9dfda032-0c00-4475-9e2d-02691570fc8c")
        private TableViewer existingElementTable;

        @objid ("a61d6874-a483-42fd-aba6-2f5903f0bfeb")
        private Text nameText;

        @objid ("62164536-bbe3-4d4a-9f98-8a2daceece30")
        private Text descriptionText;

        @objid ("db5a3144-5a53-4fab-82c4-d7516ea6ae16")
        private Text searchText;

        @objid ("18256eef-f9e0-4c96-9cef-20fdb6202046")
        private Image metaclassImage;

        @objid ("2c626eb9-4bb8-41db-be63-91ab28dd01d2")
        private Label loading;

        @objid ("f78ceb74-1da1-4f86-a4bb-14cd2c8fd537")
        private Button okButton;

        @objid ("217921d3-bc71-4d45-999c-a8da75e9b43a")
        public  Ui(Controler controler) {
            this.controler = controler;
        }

        @objid ("63210fac-9837-4891-b089-6daf6a68a431")
        public Composite create(Composite parent) {
            this.rootComposite = new Composite(parent, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
            this.rootComposite.setLayoutData(gd);
            this.rootComposite.setFont(parent.getFont());
            PolluxWidgetConfigurator.configureContainer(this.rootComposite);
            createHeaderArea(this.rootComposite);
            createCoreArea(this.rootComposite);
            return this.rootComposite;
        }

        @objid ("11ab1bfd-1994-483f-85f3-f1c011b5a951")
        private Composite createHeaderArea(Composite parent) {
            this.headerLabel = new CLabel(parent, SWT.NONE);
            PolluxWidgetConfigurator.configureHeaderField(headerLabel);
            return headerLabel;
        }

        @objid ("f5ad71c4-f8fd-4a66-b50a-35f203895d82")
        private void createCoreArea(Composite parent) {
            SashForm sashform = new SashForm(parent, SWT.HORIZONTAL);
            
            GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
            gd.horizontalSpan = 0;
            sashform.setLayoutData(gd);
            sashform.setSashWidth(1);
            
            createElementsTable(sashform);
            createElementPropertyTab(sashform);
            
            sashform.setWeights(new int[] { 4, 6 });
            
        }

        @objid ("4e94e89f-6f85-46dd-ad06-8c67b9ce50fd")
        private Composite createElementsTable(SashForm sashform) {
            Composite listComposite = new Composite(sashform, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_BOTH);
            listComposite.setLayoutData(gd);
            PolluxWidgetConfigurator.configureContainer(listComposite);
            
            Label title = new Label(listComposite, SWT.NONE);
            title.setText(DiagramElements.I18N.getString("elementselection.elementtable.label"));
            PolluxWidgetConfigurator.configureStyleForFieldLabel(title);
            
            PolluxFieldBuilder fb = new PolluxFieldBuilder(listComposite);
            
            createSearchComposite(listComposite);
            
            this.searchText.addListener(SWT.Modify, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    controler.onSerachUpdate(searchText.getText());
                }
            });
            
            this.existingElementTable = new TableViewer(listComposite, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
            
            this.existingElementTable.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
            this.existingElementTable.setContentProvider(ArrayContentProvider.getInstance());
            
            TableViewerColumn elementListColumn = new TableViewerColumn(this.existingElementTable, SWT.FILL);
            elementListColumn.getColumn().setAlignment(SWT.CENTER);
            elementListColumn.getColumn().setResizable(false);
            
            // Universal Label Provider
            elementListColumn.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public String getText(Object element) {
                    if (element instanceof ModelElement) {
                        ModelElement p = (ModelElement) element;
                        return p.getName();
                    } else {
                        ElementPlaceolderData p = (ElementPlaceolderData) element;
                        return DiagramElements.I18N.getMessage("elementselection.placeolder.new.label", p.getMetaclass().getName());
                    }
            
                }
            
                @Override
                public Image getImage(Object element) {
                    if (element instanceof ModelElement) {
                        return getMetaclassImage(((ModelElement) element).getMClass());
                    } else {
                        return UIImages.ADD;
                    }
                }
            
            });
            
            this.existingElementTable.getControl().addControlListener(new ControlListener() {
                @Override
                public void controlResized(ControlEvent e) {
                    Rectangle rect = existingElementTable.getTable().getClientArea();
                    if (rect.width > 0) {
                        elementListColumn.getColumn().setWidth(rect.width);
                    }
                }
            
                @Override
                public void controlMoved(ControlEvent e) {
                    // TODO Auto-generated method stub
            
                }
            });
            
            this.existingElementTable.addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    IStructuredSelection selection = existingElementTable.getStructuredSelection();
                    controler.onElementSelectionChange(selection.getFirstElement());
                }
            });
            
            this.loading = new Label(listComposite, SWT.NONE);
            GridData labelGd = new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1);
            this.loading.setLayoutData(labelGd);
            this.loading.setText(DiagramElements.I18N.getString("elementselection.loading"));
            PolluxWidgetConfigurator.configureStyleForFieldLabel(this.loading);
            return listComposite;
        }

        @objid ("a8c56f8e-349e-4922-8fc2-c1c373d6ced6")
        private Composite createSearchComposite(Composite composite) {
            Composite searchComposite = new Composite(composite, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_HORIZONTAL);
            searchComposite.setLayoutData(gd);
            PolluxWidgetConfigurator.configureContainer(searchComposite);
            
            GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 2;
            
            searchComposite.setLayout(gridLayout);
            Label searchImage = new Label(searchComposite, SWT.NONE);
            searchImage.setImage(UIImages.SEARCH);
            searchImage.setBackground(UIColor.WHITE);
            searchImage.setSize(30, 30);
            this.searchText = new Text(searchComposite, SWT.BORDER | SWT.WRAP | SWT.SINGLE);
            this.searchText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
            return searchComposite;
        }

        @objid ("812ebb2c-7972-40d3-b1fe-031024279d77")
        private Composite createElementPropertyTab(SashForm sashform) {
            Composite elementPropertyComposite = new Composite(sashform, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_BOTH);
            elementPropertyComposite.setLayoutData(gd);
            
            PolluxWidgetConfigurator.configureContainer(elementPropertyComposite);
            
            Label title = new Label(elementPropertyComposite, SWT.NONE);
            title.setText(DiagramElements.I18N.getString("elementselection.newelement.description"));
            PolluxWidgetConfigurator.configureStyleForFieldLabel(title);
            
            PolluxFieldBuilder fb = new PolluxFieldBuilder(elementPropertyComposite);
            
            // Element name
            this.nameText = fb.createSimpleField(
                    DiagramElements.I18N.getString("elementselection.element.name.label"),
                    () -> new Text(elementPropertyComposite, SWT.BORDER), null);
            this.nameText.setFocus();
            setTextEnterCallback(this.nameText, s -> this.controler.onNameChange(s));
            
            // Element description
            this.descriptionText = fb.createMultiField(
                    DiagramElements.I18N.getString("elementselection.element.description.label"),
                    () -> new Text(elementPropertyComposite, SWT.BORDER | SWT.WRAP | SWT.MULTI), "");
            
            setTextEnterCallback(this.descriptionText, s -> this.controler.onDescriptionChange(s));
            return elementPropertyComposite;
        }

        @objid ("cf086725-7e46-42df-9e40-980adec1668c")
        public void update(ElementSelectionData data) {
            this.headerLabel.setText(DiagramElements.I18N.getMessage("elementselection.dialog.description", data.getMetaclass().getName()));
            this.headerLabel.setImage(getMetaclassImage(data.getMetaclass()));
            
            if (data.getCandidates() != null) {
                this.existingElementTable.setInput(data.getFiltredCandidates());
            }
            this.existingElementTable.setSelection(new StructuredSelection(data.getSelectedElement()));
            
            Object selection = data.getSelectedElement();
            if (selection instanceof ModelElement) {
                ModelElement mselection = (ModelElement) selection;
                this.nameText.setText(((ModelElement) selection).getName());
                String description = mselection.getNoteContent("ModelerModule", "Infrastructure.ModelElement", "description");
                if (description != null) {
                    this.descriptionText.setText(description);
                }
                this.nameText.setEnabled(false);
                this.descriptionText.setEnabled(false);
                this.okButton.setEnabled(true);
            } else if (selection instanceof ElementPlaceolderData) {
                this.nameText.setText(((ElementPlaceolderData) selection).getName());
                this.descriptionText.setText(((ElementPlaceolderData) selection).getDescription());
                this.nameText.setEnabled(true);
                this.descriptionText.setEnabled(true);
                if (this.okButton != null) {
                    this.okButton.setEnabled(!"".equals(this.nameText.getText()));
                }
            }
            
            if (data.isLodead()) {
                this.loading.setVisible(false);
            } else {
                this.loading.setVisible(true);
            }
            
        }

        @objid ("ade95547-6122-46ba-aeae-df1bcd208c35")
        private Image getMetaclassImage(MClass metaclass) {
            if (this.metaclassImage == null) {
                this.metaclassImage = MetamodelImageService.getIcon(metaclass);
            }
            return this.metaclassImage;
        }

        @objid ("3c1a06fa-63c8-46b3-8097-087e03b27b84")
        public void setTextEnterCallback(Text text, Consumer<String> callback) {
            text.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent ev) {
                    callback.accept(((Text) ev.widget).getText());
                };
            });
            text.addTraverseListener(new TraverseListener() {
                @Override
                public void keyTraversed(TraverseEvent e) {
                    switch (e.detail) {
                    case SWT.TRAVERSE_RETURN:
                        Text textControl = (Text) e.widget;
                        callback.accept(textControl.getText());
            
                        if ((textControl.getStyle() & SWT.MULTI) == 0) {
                            textControl.getParent().forceFocus();
                            e.detail = SWT.TRAVERSE_TAB_NEXT;
                        }
                    }
                }
            });
            
            text.addListener(SWT.Modify, new Listener() {
                @Override
                public void handleEvent(Event ev) {
                    callback.accept(((Text) ev.widget).getText());
                }
            });
            
        }

        @objid ("82ceb39e-18b8-4b89-a526-c469b6ee381f")
        public void setOkButton(Button button) {
            this.okButton = button;
        }

    }

}

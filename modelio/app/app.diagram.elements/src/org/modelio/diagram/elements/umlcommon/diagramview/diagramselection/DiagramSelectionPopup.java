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
package org.modelio.diagram.elements.umlcommon.diagramview.diagramselection;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog2;
import org.modelio.platform.ui.dialog.PolluxWidgetConfigurator;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("fbf3eca2-7088-4d59-a3df-91a6fea67b3b")
public class DiagramSelectionPopup extends ModelioDialog2 {
    @objid ("bda89850-39b3-4d36-9b9c-fa2469367928")
    private DiagramSelectionModel data;

    @objid ("49888a8c-c682-4f74-a21b-d946f5fe92b1")
    private Controler controler;

    @objid ("2973ed64-22d9-4059-9760-ce322f7b27f8")
    private Ui ui;

    @objid ("cbba8eb7-41f3-4975-8d00-4dd32b150284")
    public  DiagramSelectionPopup(Shell parentShell) {
        super(parentShell);
        this.controler = new Controler();
        this.ui = new Ui(this.controler);
        this.controler.setUi(this.ui);
        
    }

    @objid ("d4f1d77b-7484-488c-95d0-1bcedf39b55d")
    @Override
    protected Point getInitialSize() {
        return new Point(400, 400);
    }

    @objid ("250e96e5-f038-40af-a799-6f036018367d")
    @Override
    protected void addButtonsInButtonBar(Composite parent) {
        addDefaultButtons(parent);
        getButton(IDialogConstants.OK_ID).setEnabled(true);
        this.ui.setOkButton(getButton(IDialogConstants.OK_ID));
        
    }

    @objid ("90373794-010d-4a05-ad93-ca1629e6a406")
    @Override
    protected Control createContentArea(Composite parent) {
        Composite top = this.ui.create(parent);
        if (this.data != null) {
            this.controler.updateData(data);
        }
        return top;
    }

    @objid ("a9b9f60e-d430-4b08-9c02-17a1c30f138c")
    @Override
    protected void init() {
        getShell().setText(DiagramElements.I18N.getMessage("DiagramSelectionPopup.WindowTitle"));
    }

    @objid ("b09d3760-b7ee-4f0c-a512-b7c07a475e76")
    public void setInput(DiagramSelectionModel data) {
        this.data = data;
    }

    @objid ("e88b9e39-d279-4d9e-806b-78a0f70b4c9d")
    private static class Controler {
        @objid ("10553160-467b-469c-ba0c-5338c4541479")
        private Ui ui;

        @objid ("4e142b38-3d51-445e-acd7-c532a3a55faa")
        private List<AbstractDiagram> filtredData = new ArrayList<>();

        @objid ("c9fad25b-2c87-4246-96c0-0a45caee2961")
        private DiagramSelectionModel data;

        @objid ("5766069d-0586-487a-8e6f-8da9a25be1c1")
        public void setUi(Ui ui) {
            this.ui = ui;
        }

        @objid ("df11d156-601f-43d5-9380-eda39eda8f5e")
        public void updateData(DiagramSelectionModel data) {
            this.data = data;
            this.filtredData = new ArrayList<>(this.data.getAllowed());
            this.ui.update(data,new ArrayList<>(this.data.getAllowed()));
            
        }

        @objid ("37232304-7568-4fb2-b0a1-5f0607afaea1")
        public void onSerachUpdate(String searchText) {
            this.filtredData = new ArrayList<>();
            for (AbstractDiagram candidate : this.data.getAllowed()) {
                if (candidate.getName().toLowerCase().contains(searchText.toLowerCase()) ||(candidate.isValid() &&  candidate.getCompositionOwner() != null  && candidate.getCompositionOwner().getMClass().getName().toLowerCase().contains(searchText.toLowerCase()))) {
                    filtredData.add(candidate);
                }
            }
            
            this.ui.update(this.data,this.filtredData);
            
        }

        @objid ("d2e8d435-aa1b-4bd5-814e-0b886c4b3b14")
        public void onElementSelectionChange(AbstractDiagram element) {
            this.data.setSelected(element);
            this.ui.update(this.data, this.filtredData);
            
        }

    }

    @objid ("a0b4fab4-1ca1-4a64-9c9b-14c55b61bba8")
    private static class Ui {
        @objid ("0c2520d0-c2e9-4ef0-a015-09a67a7444e3")
        private Composite rootComposite;

        @objid ("3e9d2c9a-74a3-46ba-bb02-eafae5e6d6fb")
        private CLabel headerLabel;

        @objid ("8b4c0623-515a-4329-b4f7-eb7babb41cfa")
        private TableViewer existingElementTable;

        @objid ("32e75993-51a0-43d9-97ab-4e0ca3c2c27f")
        private Text searchText;

        @objid ("77a68cce-a18b-44c1-8a57-300fb20936cc")
        private Button okButton;

        @objid ("1fa1c5b3-79b9-4b01-9908-3ef6ce5b3722")
        private Controler controler;

        @objid ("68243fff-f8eb-4c7b-a385-3a91ec2ee313")
        public  Ui(Controler controler) {
            this.controler = controler;
        }

        @objid ("acdad73a-5f35-4274-b923-d2dd0f7a0c6f")
        public Composite create(Composite parent) {
            this.rootComposite = new Composite(parent, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
            this.rootComposite.setLayoutData(gd);
            this.rootComposite.setFont(parent.getFont());
            PolluxWidgetConfigurator.configureContainer(this.rootComposite);
            createHeaderArea(this.rootComposite);
            createElementsTable(this.rootComposite);
            return this.rootComposite;
        }

        @objid ("4a694a9f-33a5-425a-95d0-6b03d5584261")
        private Composite createHeaderArea(Composite parent) {
            this.headerLabel = new CLabel(parent, SWT.NONE);
            PolluxWidgetConfigurator.configureHeaderField(this.headerLabel);
            return headerLabel;
        }

        @objid ("7dd96ac5-6588-4804-941e-b96c452cefde")
        private Composite createElementsTable(Composite parent) {
            Composite listComposite = new Composite(parent, SWT.NONE);
            GridData gd = new GridData(GridData.FILL_BOTH);
            listComposite.setLayoutData(gd);
            PolluxWidgetConfigurator.configureContainer(listComposite);
            
            createSearchComposite(listComposite);
            
            this.searchText.addListener(SWT.Modify, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    controler.onSerachUpdate(searchText.getText());
                }
            });
            
            this.existingElementTable = new TableViewer(listComposite, SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
            
            this.existingElementTable.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
            this.existingElementTable.setContentProvider(ArrayContentProvider.getInstance());
            
            TableViewerColumn elementListColumn = new TableViewerColumn(this.existingElementTable, SWT.FILL);
            elementListColumn.getColumn().setAlignment(SWT.CENTER);
            elementListColumn.getColumn().setResizable(false);
            
            // Universal Label Provider
            elementListColumn.setLabelProvider(new ColumnLabelProvider() {
                @Override
                public Image getImage(final Object obj) {
                    if (obj instanceof AbstractDiagram) {
                        return ElementImageService.getIcon((AbstractDiagram) obj);
                    }
                    return super.getImage(obj);
                }
            
                @Override
                public String getText(final Object obj) {
                    if (obj instanceof AbstractDiagram) {
                        return ((AbstractDiagram) obj).getName()+  " " +DiagramElements.I18N.getMessage("DiagramSelectionPopup.forminfo",((AbstractDiagram) obj).getCompositionOwner().getName() );
                    }
                    return super.getText(obj);
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
                    controler.onElementSelectionChange((AbstractDiagram)selection.getFirstElement());
            
                }
            });
            return listComposite;
        }

        @objid ("191aa643-8c4e-46ec-b44e-69e40e1cdfad")
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

        @objid ("5ceb0e51-cc0f-4c91-8dd8-669e2982ac86")
        public void update(DiagramSelectionModel data, List<AbstractDiagram> filtresData) {
            this.headerLabel.setText(DiagramElements.I18N.getMessage("DiagramSelectionPopup.DialogTitle"));
            this.existingElementTable.setInput(filtresData);
            
                    /*    if(data.getSelected() != null) {
                this.existingElementTable.setSelection(new StructuredSelection(data.getSelected()));
            }*/
            
            if (this.okButton != null) {
                this.okButton.setEnabled(data.getSelected() != null);
            }
            
        }

        @objid ("5ac3f788-922e-469b-80c6-87b410dc8f54")
        private Image getMetaclassImage(MClass metaclass) {
            return MetamodelImageService.getIcon(metaclass);
        }

        @objid ("b671dd5b-ebc8-4f79-93f5-7bd670629123")
        public void setOkButton(Button button) {
            this.okButton = button;
            this.okButton.setEnabled(false);
            
        }

    }

}

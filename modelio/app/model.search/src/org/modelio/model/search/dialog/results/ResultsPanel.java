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

package org.modelio.model.search.dialog.results;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.core.ui.swt.images.AbstractModelioElementLabelProvider;
import org.modelio.core.ui.swt.images.ElementDecoratedStyledLabelProvider;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.core.ui.swt.images.ElementStyler;
import org.modelio.core.ui.swt.images.StandardModelStyleProvider;
import org.modelio.core.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.model.search.plugin.ModelSearch;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("ccf7d325-0d5f-4a70-8515-0947b65cd873")
public class ResultsPanel {
    @objid ("770b9119-d27c-4d63-b305-067aec6dcb4d")
    private Group topGroup;

    @objid ("6956c4bd-e9ed-4e8a-b13f-8c287518dc96")
    private TableViewer resultsViewer = null;

    @objid ("e8fe62a5-4c81-4fab-8457-b83c580e5939")
    private List<Element> results;

    @objid ("5580f7d3-0d83-406a-8153-04af7255fdd7")
    private IModelioNavigationService navigationService;

    @objid ("d18d5d6a-165a-4f9c-8221-8f3a794e33cc")
    public void showResults(List<Element> res) {
        this.results = res;
        this.resultsViewer.setInput(res);
        
        if (res != null) {
            final int nMatch = res.size();
            this.topGroup.setText(ModelSearch.I18N.getMessage("SearchDialog.results", Integer.toString(nMatch))); //$NON-NLS-1$
        }
    }

    @objid ("15ad13fd-3d61-4c9e-b22b-d6682a0bc662")
    public Control getControl() {
        return this.topGroup;
    }

    @objid ("c38077f7-43fd-4680-89fd-9ad83f4cc64d")
    public ResultsPanel(Composite parent, IModelioNavigationService navigationService) {
        this.navigationService = navigationService;
        this.topGroup = new Group(parent, SWT.NONE);
        this.topGroup.setText(ModelSearch.I18N.getMessage("SearchDialog.initial")); //$NON-NLS-1$
        this.topGroup.setFont(CoreFontRegistry.getModifiedFont(this.topGroup.getFont(), SWT.BOLD, 1.0f));
        
        final GridLayout gridLayout2 = new GridLayout();
        gridLayout2.numColumns = 1;
        this.topGroup.setLayout(gridLayout2);
        
        this.resultsViewer = new TableViewer(this.topGroup, SWT.MULTI | SWT.FULL_SELECTION);
        this.resultsViewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
        
        ResultsViewerComparator comparator = new ResultsViewerComparator();
        
        // Make lines and make header visible
        final Table table = this.resultsViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        final UniversalLabelProvider universalLabelProvider = new UniversalLabelProvider();
        
        // First column: element metaclass icon
        final TableViewerColumn viewerColumn1 = new TableViewerColumn(this.resultsViewer, SWT.NONE);
        final TableColumn column1 = viewerColumn1.getColumn();
        column1.addSelectionListener(getSelectionAdapter(comparator, column1, 0));
        column1.setWidth(35);
        column1.setResizable(false);
        column1.setMoveable(false);
        
        viewerColumn1.setLabelProvider(new ElementDecoratedStyledLabelProvider(new AbstractModelioElementLabelProvider() {
            @Override
            public String getText(Object element) {
                return "";
            }
        
            @Override
            public Image getImage(Object obj) {
                return universalLabelProvider.getImage(obj);
            }
        
            @Override
            public StyledString getStyledText(Object object) {
                return new StyledString();
            }
        
        }, true, false));
        
        // Seconde column: element name without icon
        final TableViewerColumn viewerColumn2 = new TableViewerColumn(this.resultsViewer, SWT.NONE);
        final TableColumn column2 = viewerColumn2.getColumn();
        column2.addSelectionListener(getSelectionAdapter(comparator, column2, 1));
        
        column2.setText(ModelSearch.I18N.getString("SearchDialog.results.name"));
        column2.setWidth(250);
        column2.setResizable(true);
        column2.setMoveable(false);
        
        viewerColumn2.setLabelProvider(new StyledCellLabelProvider() {
            @Override
            public void update(ViewerCell cell) {
                StyledString styledStr = universalLabelProvider.getStyledText(cell.getElement());
                cell.setText(styledStr.getString());
                cell.setStyleRanges(styledStr.getStyleRanges());
        
                super.update(cell);
            }
        });
        
        // Second column: element name space
        final TableViewerColumn viewerColumn3 = new TableViewerColumn(this.resultsViewer, SWT.NONE);
        final TableColumn column3 = viewerColumn3.getColumn();
        column3.addSelectionListener(getSelectionAdapter(comparator, column3, 2));
        column3.setText(ModelSearch.I18N.getString("SearchDialog.results.from"));
        column3.setWidth(250);
        column3.setResizable(true);
        column3.setMoveable(false);
        viewerColumn3.setLabelProvider(new StyledCellLabelProvider() {
        
            @Override
            public void update(ViewerCell cell) {
                final Element e = (Element) cell.getElement();
                cell.setImage(null);
        
                if (false) {
                    final String text = makeText(e);
                    cell.setText(text);
                    cell.setStyleRanges(StandardModelStyleProvider.getStyleRanges(e, text));
                } else {
        
                    StyledString t2 = makeStyledText(e);
                    cell.setText(t2.getString());
                    cell.setStyleRanges(t2.getStyleRanges());
                }
            }
        
            private String makeText(MObject obj) {
                final StringBuilder text = new StringBuilder();
                MObject owner = obj.getCompositionOwner();
                while (owner != null) {
                    if (owner != obj.getCompositionOwner()) {
                        text.insert(0, '.');
                    }
        
                    String name = owner.getName();
                    if (name.isEmpty()) {
                        name = universalLabelProvider.getText(owner);
                    }
        
                    text.insert(0, name);
                    owner = owner.getCompositionOwner();
                }
                return text.toString();
            }
        
            private StyledString makeStyledText(MObject obj) {
                StyledString text = new StyledString();
                final StyledString dot = new StyledString(".");
        
                /*
                 * new Styler() {
                 * 
                 * @Override public void applyStyles(TextStyle textStyle) { textStyle.font = CoreFontRegistry.getModifiedFont(getControl().getFont(), SWT.BOLD, 1.0f); } });
                 */
        
                MObject owner = obj.getCompositionOwner();
                boolean first = true;
                while (owner != null) {
                    if (first) {
                        first = false;
                    } else {
                        text = concat(dot, text);
                    }
        
                    String name = owner.getName();
                    if (name.isEmpty()) {
                        text = concat(universalLabelProvider.getStyledText(owner), text);
                    } else {
                        text = concat(new StyledString(name, ElementStyler.getStyler(owner)), text);
                    }
        
                    owner = owner.getCompositionOwner();
                }
                return text;
            }
        
            private StyledString concat(StyledString s1, StyledString s2) {
                StyledString ret = new StyledString();
                ret.append(s1);
                ret.append(s2);
                return ret;
            }
        
        });
        
        this.resultsViewer.setContentProvider(new ResultsViewerContentProvider(this.results));
        
        this.resultsViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        
                if (!selection.isEmpty()) {
                    ArrayList<MObject> selected = new ArrayList<>();
                    for (Object o : selection.toList()) {
                        if (o instanceof Note) {
                            selected.add(((Note) o).getSubject());
                        } else if (o instanceof MObject) {
                            selected.add((MObject) o);
                        }
                    }
                    ResultsPanel.this.navigationService.fireNavigate(selected);
                }
            }
        });
        
        this.resultsViewer.setComparator(comparator);
        this.resultsViewer.setInput(null);
    }

    @objid ("dba7645d-f47f-45d3-91de-f39986a22d85")
    private SelectionAdapter getSelectionAdapter(ResultsViewerComparator comparator, final TableColumn column, final int index) {
        SelectionAdapter selectionAdapter = new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                comparator.setColumn(index);
                int dir = comparator.getDirection();
                ResultsPanel.this.resultsViewer.getTable().setSortDirection(dir);
                ResultsPanel.this.resultsViewer.getTable().setSortColumn(column);
                ResultsPanel.this.resultsViewer.refresh();
            }
        };
        return selectionAdapter;
    }

    @objid ("e2828b9c-e203-4a0f-b58d-641055580bd3")
    public static class ResultsViewerContentProvider implements IStructuredContentProvider {
        @objid ("7f201c7b-f557-4020-8921-6c58d91d83ed")
        private List<Element> results = null;

        @objid ("57d306ee-9cf5-4a83-b129-880c2a47fd62")
        @Override
        public Object[] getElements(Object arg0) {
            return this.results.toArray();
        }

        @objid ("47bc9640-b9ed-435b-9f1d-9ffbbe54c2db")
        @Override
        public void dispose() {
        }

        @objid ("76a63686-5db6-447d-a9f2-2ae2366d827b")
        @SuppressWarnings ("unchecked")
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            this.results = (List<Element>) newInput;
        }

        @objid ("eb39915e-5668-48bd-9647-704e31a3966b")
        public ResultsViewerContentProvider(List<Element> results) {
            this.results = results;
        }

    }

    @objid ("92e8d1bd-330b-4d45-bf3b-e90588911bfd")
    public static class ResultsViewerComparator extends ViewerComparator {
        @objid ("9ac214e5-2564-400a-86ff-efe261870430")
        private int propertyIndex;

        @objid ("f8b8aaff-2843-40a6-8004-7660fe034710")
        private static final int DESCENDING = 1;

        @objid ("026a76d5-4871-42bd-bb28-f91c7e2a1f59")
        private int direction = ResultsViewerComparator.DESCENDING;

        @objid ("89865c32-07e5-43e5-af43-ac4a9bab2f46")
        @Override
        public int compare(Viewer viewer, Object e1, Object e2) {
            final MObject o1 = (MObject) e1;
            final MObject o2 = (MObject) e2;
            int rc = 0;
            switch (this.propertyIndex) {
            case 0:
                rc = compareMetaclasses(o1, o2);
                break;
            case 1:
            
                String name1 = o1.getName();
                String name2 = o2.getName();
                if (o1 instanceof Note) {
                    name1 = ((Note) o1).getModel().getName();
                }
            
                if (o2 instanceof Note) {
                    name2 = ((Note) o2).getModel().getName();
                }
                name1 = name1.toLowerCase();
                name2 = name2.toLowerCase();
            
                // Empty names are always an the end
                if (name1.equals("") && !name2.equals("")) {
                    rc = 2;
                } else if (name2.equals("") && !name1.equals("")) {
                    rc = -2;
                } else {
                    rc = name1.compareTo(name2);
                }
                break;
            case 2:
                rc = compareFrom(o1, o2);
                break;
            default:
                rc = 0;
            }
            if (this.direction == ResultsViewerComparator.DESCENDING) {
                rc = -rc;
            }
            return rc;
        }

        @objid ("a614211b-ee04-4f80-aad2-450508bc417b")
        private int compareMetaclasses(MObject o1, MObject o2) {
            return o1.getMClass().getName().compareTo(o2.getMClass().getName());
        }

        @objid ("1a83a7fd-801e-47e9-9465-d6683727fb73")
        private int compareFrom(MObject o1, MObject o2) {
            String text1 = calculeNamespace(o1);
            String text2 = calculeNamespace(o2);
            return text1.compareTo(text2);
        }

        @objid ("5ff72c61-ba18-4aaa-ad17-11550a64fe8b")
        public ResultsViewerComparator() {
            this.propertyIndex = 1;
            this.direction = 0;
        }

        @objid ("f92a5da6-7f49-47fe-8d12-79b8df940c33")
        public int getDirection() {
            return this.direction == 1 ? SWT.DOWN : SWT.UP;
        }

        @objid ("184e9107-5251-48f4-8515-e5e50d97c677")
        public void setColumn(int column) {
            if (column == this.propertyIndex) {
                // Same column as last sort; toggle the direction
                this.direction = 1 - this.direction;
            } else {
                // New column; do an ascending sort
                this.propertyIndex = column;
                this.direction = 0;
            }
        }

        @objid ("90ff36a7-c7f9-40cf-bf76-9397f318a8bf")
        private String calculeNamespace(MObject obj) {
            final StringBuffer text = new StringBuffer();
            MObject parent = obj.getCompositionOwner();
            while (parent != null) {
                if (parent != obj.getCompositionOwner()) {
                    text.insert(0, '.');
                }
            
                text.insert(0, parent.getName());
                parent = parent.getCompositionOwner();
            }
            return text.toString();
        }

    }

    @objid ("8f344be5-bac1-48a2-ae4d-33bd498e5dcc")
    public static class ResultsViewerLabelProvider extends AbstractModelioElementLabelProvider {
        @objid ("ad1e411a-54b2-421a-b218-ad8d0e2e5ed7")
        @Override
        public Image getImage(Object obj) {
            final Element element = (Element) obj;
            return ElementImageService.getIcon(element);
        }

        @objid ("dd214ec5-53f2-41d5-9b75-b19c20e2da7d")
        @Override
        public String getText(Object obj) {
            if (obj instanceof NameSpace) {
                final NameSpace ns = (NameSpace) obj;
                String parentStr = new String();
                ModelTree parent = ns.getOwner();
                while (parent != null) {
                    parentStr = parent.getName() + "." + parentStr; //$NON-NLS-1$
                    parent = parent.getOwner();
                }
                return parentStr + ns.getName();
            } else {
                return obj.toString();
            }
        }

        @objid ("f3b016e4-b59e-4c3a-a1a8-2effde927343")
        @Override
        public StyledString getStyledText(Object element) {
            return new StyledString(getText(element));
        }

    }

}

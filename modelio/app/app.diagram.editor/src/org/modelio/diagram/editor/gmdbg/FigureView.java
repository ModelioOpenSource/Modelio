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
package org.modelio.diagram.editor.gmdbg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.modelio.diagram.elements.core.figures.ChainedLayout;
import org.modelio.platform.ui.CoreFontRegistry;

@objid ("7ccc0864-4543-49ad-87ad-3329399a9c4d")
public class FigureView {
    @objid ("2b7868b4-fbcc-45fc-8132-abcc9dcc4b78")
    private TableViewer figureProps;

    @objid ("c81aa6b8-a565-4540-9bb9-5a2d5d4a4587")
    private Font boldFont;

    @objid ("4437763e-a988-4646-894e-6c51b00d2645")
    public  FigureView(Composite parent) {
        this.figureProps = new TableViewer(parent, SWT.V_SCROLL);
        
        final Table table = this.figureProps.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        this.boldFont = CoreFontRegistry.getModifiedFont(table.getFont(), SWT.BOLD, 1.0f);
        
        TableViewerColumn colKey = new TableViewerColumn(this.figureProps, SWT.NONE);
        colKey.getColumn().setWidth(200);
        colKey.getColumn().setText("Figure Property");
        colKey.setLabelProvider(new ColumnLabelProvider() {
            @SuppressWarnings ("unchecked")
            @Override
            public String getText(Object element) {
                return ((Entry<String, String>) element).getKey();
            }
        
            @Override
            public Font getFont(Object element) {
                return getText(element).startsWith(" ") ? null : FigureView.this.boldFont;
            }
        });
        
        TableViewerColumn colValue = new TableViewerColumn(this.figureProps, SWT.NONE);
        colValue.getColumn().setWidth(200);
        colValue.getColumn().setText("Value");
        colValue.setLabelProvider(new ColumnLabelProvider() {
            @SuppressWarnings ("unchecked")
            @Override
            public String getText(Object element) {
                return ((Entry<String, String>) element).getValue();
            }
        
            @Override
            public Font getFont(Object element) {
                return ((Entry<String, String>) element).getKey().startsWith(" ") ? null : FigureView.this.boldFont;
            }
        });
        
        this.figureProps.setContentProvider(new FigureContentProvider());
        
    }

    @objid ("a545ec63-44b5-42a7-b7d3-7ae459102bbc")
    public TableViewer getTableViewer() {
        return this.figureProps;
    }

    @objid ("42d8e897-2bb7-41c9-8d37-7de1f8d0add3")
    public void setInput(GraphicalEditPart ep) {
        this.figureProps.setInput(ep);
    }

    @objid ("c0c70427-1be7-4476-b524-6fac3e75757d")
    private static class FigureContentProvider implements IStructuredContentProvider {
        @objid ("9bd8e3c9-51ba-420c-81b2-64b259da5fec")
        List<Entry<String, String>> properties;

        @objid ("48a52f83-1a76-4746-abaf-29e1082cc452")
        @Override
        public void dispose() {
            this.properties = null;
        }

        @objid ("58d66acc-91e6-4f44-a948-b9dfae158666")
        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            this.properties = new ArrayList<>();
            
            if (newInput == null) {
                return;
            }
            
            // Connection figures are special
            if (newInput instanceof AbstractConnectionEditPart) {
                AbstractConnectionEditPart ep = (AbstractConnectionEditPart) newInput;
            
                Connection connectionFigure = ep.getConnectionFigure();
                this.properties.add(new XEntry("Type", connectionFigure.getClass().getSimpleName()));
            
                ConnectionRouter router = connectionFigure.getConnectionRouter();
                this.properties.add(new XEntry("    router", router.getClass().getSimpleName()));
            
                this.properties.add(new XEntry("Path", ""));
            
            
                ConnectionAnchor sourceAnchor = connectionFigure.getSourceAnchor();
                if (sourceAnchor != null) {
                    this.properties.add(new XEntry("    source anchor", sourceAnchor.getClass().getSimpleName()));
                    this.properties.add(new XEntry("        ref point", sourceAnchor.getReferencePoint() != null ? sourceAnchor.getReferencePoint().toString() : "<null>"));
                } else {
                    this.properties.add(new XEntry("    source anchor", "<null>"));
                    this.properties.add(new XEntry("        ref point", "<null>"));
                }
            
                ConnectionAnchor targetAnchor = connectionFigure.getTargetAnchor();
                if (targetAnchor != null) {
                    this.properties.add(new XEntry("    target anchor", targetAnchor.getClass().getSimpleName()));
                    this.properties.add(new XEntry("        ref point", targetAnchor.getReferencePoint() != null ? targetAnchor.getReferencePoint().toString() : "<null>"));
                } else {
                    this.properties.add(new XEntry("    target anchor", "<null>"));
                    this.properties.add(new XEntry("        ref point", "<null>"));
                }
            
                Object routingConstraint = router.getConstraint(connectionFigure);
                this.properties.add(new XEntry("    route constraint", Formatter.toString(routingConstraint)));
            
                this.properties.add(new XEntry("Points", ""));
                PointList points = connectionFigure.getPoints();
                for (int i=0; i<points.size(); i++) {
                    this.properties.add(new XEntry("    pt"+i, points.getPoint(i).toString()));
                }
            
            
            } else if (newInput instanceof AbstractGraphicalEditPart) {
                AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart) newInput;
            
                IFigure figure = ep.getFigure();
                Rectangle absBounds = figure.getBounds().getCopy();
                figure.translateToAbsolute(absBounds);
                this.properties.add(new XEntry("Type", figure.getClass().getSimpleName()));
                this.properties.add(new XEntry("    bounds relative", Formatter.toString(figure.getBounds())));
                this.properties.add(new XEntry("    bounds absolute", Formatter.toString(absBounds)));
                this.properties.add(new XEntry("    insets", Formatter.toString(figure.getInsets())));
                this.properties.add(new XEntry("    client area", Formatter.toString(figure.getClientArea())));
                this.properties.add(new XEntry("    size", Formatter.toString(figure.getSize())));
            
                this.properties.add(new XEntry("    min size", Formatter.toString(figure.getMinimumSize())));
                this.properties.add(new XEntry("    preferred size", Formatter.toString(figure.getPreferredSize())));
                this.properties.add(new XEntry("    max size", Formatter.toString(figure.getMaximumSize())));
            
                this.properties.add(new XEntry("    border", Formatter.toString(figure.getBorder())));
            
                this.properties.add(new XEntry("    background", Formatter.toString(figure.getBackgroundColor())));
                this.properties.add(new XEntry("    foreground", Formatter.toString(figure.getForegroundColor())));
                this.properties.add(new XEntry("    font", Formatter.toString(figure.getFont())));
            
                this.properties.add(new XEntry("    layout", formatLayoutManager(figure)));
                this.properties.add(new XEntry("    layout constraint", formatLayoutConstraint(figure)));
            
                this.properties.add(new XEntry("    parent", (figure.getParent() != null) ? figure.getParent().getClass().getSimpleName() : "<null>"));
                this.properties.add(new XEntry("    parent layout", formatLayoutManager(figure.getParent())));
            
            }
            
        }

        @objid ("7f63b105-4871-41f3-a9e1-4e819524743d")
        private String formatLayoutConstraint(IFigure figure) {
            IFigure parent = figure.getParent();
            if (parent == null)
                return "N/A";
            
            LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager == null)
                return "N/A";
            return Formatter.toString(layoutManager.getConstraint(figure));
        }

        @objid ("f39ed5c5-fcb5-4328-a8f0-c631100fe4fd")
        private String formatLayoutManager(IFigure figure) {
            if (figure == null)
                return "N/A";
            
            LayoutManager layoutManager = figure.getLayoutManager();
            if (layoutManager == null)
                return "<null>";
            
            if (! (layoutManager instanceof ChainedLayout))
                return layoutManager.getClass().getSimpleName();
            
            StringBuilder s = new StringBuilder();
            while (layoutManager instanceof ChainedLayout) {
                s.append(layoutManager.getClass().getSimpleName());
                s.append(" > ");
                layoutManager = ((ChainedLayout) layoutManager).getChained();
            }
            
            if (layoutManager == null)
                s.append("<null>");
            else
                s.append(layoutManager.getClass().getSimpleName());
            return s.toString();
        }

        @objid ("baf881e9-10a8-431f-9787-ac0c523b6ec8")
        @Override
        public Object[] getElements(Object inputElement) {
            return this.properties.toArray();
        }

    }

}

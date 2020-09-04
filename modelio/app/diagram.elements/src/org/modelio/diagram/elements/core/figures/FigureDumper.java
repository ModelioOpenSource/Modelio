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

package org.modelio.diagram.elements.core.figures;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

/**
 * utility class to dump Figure composition hierarchy to a string.
 * @author cma
 */
@objid ("d8970f8e-3733-4f76-bcae-594dde861ec6")
public class FigureDumper {
    @objid ("251d70e4-946e-4fab-a38a-f3a0d19aef58")
    private static final String INDENT_INC = "   ";

    @objid ("2f223788-563b-42fd-9609-8020091cc3de")
    private Predicate<IFigure> selector;

    @objid ("27aa8ced-c294-4c24-8ea0-1b2a8ac44820")
    private final StringBuilder out = new StringBuilder();

    @objid ("49b01afa-f877-475a-b6d9-5f122af3d7c6")
    public FigureDumper withFilter(Predicate<IFigure> filter) {
        this.selector = filter;
        return this;
    }

    @objid ("e3380ca2-af8b-4bf9-b1eb-4a440951b9ef")
    public FigureDumper withOnlyInvalidfigures() {
        this.selector = new IsValidatedFigSelector().negate();
        return this;
    }

    @objid ("e586b9dc-e8c6-4bf5-9222-4e2ec9dcbdc3")
    public String dump(IFigure fig) {
        dump(fig, "");
        return this.out.toString();
    }

    @objid ("3fb84722-8b01-4774-8bb5-9a029b874675")
    protected void dumpConnection(Connection fig, String indent) {
        this.out.append(indent);
        this.out.append("source = ");
        dumpConnectionAnchor(fig.getSourceAnchor(), indent);
        this.out.append("\n");
        
        this.out.append(indent);
        this.out.append("target = ");
        dumpConnectionAnchor(fig.getTargetAnchor(), indent);
        this.out.append("\n");
        
        this.out.append(indent);
        this.out.append("routing constraint = ");
        this.out.append(fig.getRoutingConstraint());
        //dumpRoutingConstraint(fig.getRoutingConstraint(), out, indent);
        this.out.append("\n");
        
        
        this.out.append(indent);
        this.out.append("points = (");
        PointList points = fig.getPoints();
        
        for (int i=0; i< points.size(); i++) {
            if (i != 0) {
                this.out.append(", ");
            }
            Point p = points.getPoint(i);
            this.out.append(p);
        }
        this.out.append(")\n");
    }

    @objid ("164cb1d8-ef62-4629-b90e-bd2c10b74ff4")
    protected void dumpConnectionAnchor(ConnectionAnchor sourceAnchor, String indent) {
        if (sourceAnchor == null) {
            this.out.append("<null>");
        } else {
            this.out.append(String.format(
                    "%s [ref = %s, owner=%s]",
                    sourceAnchor.getClass().getSimpleName() ,
                    sourceAnchor.getReferencePoint(),
                    getFigureResume(sourceAnchor.getOwner())));
        }
    }

    @objid ("222b1cda-da25-4afc-a643-80f9504b8748")
    protected void dump(IFigure fig, String indent) {
        this.out.append(indent);
        this.out.append("+ ");
        this.out.append(getFigureResume(fig));
        
        dumpLayoutConstraint(fig, indent);
        
        this.out.append("\n");
        
        String subIndent = indent+ INDENT_INC+ INDENT_INC;
        if (fig instanceof Connection) {
            dumpConnection((Connection) fig, subIndent);
        }
        
        if (fig instanceof ScalableFigure) {
            this.out.append(subIndent);
            this.out.append("scale=");
            this.out.append(((ScalableFigure)fig).getScale());
            this.out.append("\n");
        }
        
        if (fig instanceof Viewport) {
            Viewport vp = (Viewport) fig;
            this.out.append(subIndent).append("location").append("=").append(vp.getViewLocation()).append("\n");
            this.out.append(subIndent).append("hscroll").append("=").append(vp.getHorizontalRangeModel()).append("\n");
            this.out.append(subIndent).append("vscroll").append("=").append(vp.getVerticalRangeModel()).append("\n");
            //out.append(subIndent).append().append("=").append();
            
        }
        
        dumpLayoutManager(fig, subIndent);
        
        String childindent = indent + INDENT_INC;
        for (Object o : fig.getChildren()) {
            IFigure c = (IFigure) o;
            if (this.selector == null || this.selector.test(c)) {
                dump(c, childindent);
            }
        }
    }

    @objid ("911d2966-2ca8-4f26-aade-bf5660867e8f")
    protected void dumpLayoutConstraint(IFigure fig, String indent) {
        IFigure parent = fig.getParent();
        if (parent != null) {
            LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager != null) {
                Object constraint = layoutManager.getConstraint(fig);
                this.out.append(" constraint=");
                this.out.append(constraint);
            }
        }
    }

    @objid ("b19bb3fb-1672-4807-ae15-49ef388449e0")
    protected String getFigureResume(IFigure fig) {
        if (fig == null) {
            return "<null>";
        }
        return String.format(
                                        "%s [%sbounds = %s, id=%h]",
                                        fig.getClass().getSimpleName() , 
                                        fig.isVisible() ? "" :"invisible " ,
                                                fig.getBounds(),
                                                java.lang.System.identityHashCode(fig));
    }

    @objid ("04ab1ccf-215c-43ec-9b33-0c0796999507")
    public FigureDumper run(IFigure fig) {
        dump(fig, "");
        return this;
    }

    @objid ("574f917f-c69f-4f96-b8cb-75784f97201a")
    public FigureDumper run(Iterable<IFigure> figs) {
        for (IFigure fig : figs) {
            dump(fig, "");
        }
        return this;
    }

    @objid ("c0acb336-6751-4583-bb83-39749eea728e")
    public String get() {
        return this.out.toString();
    }

    @objid ("0cd7681b-1eea-4ffc-ba49-0c7cf02dee62")
    protected void dumpLayoutManager(IFigure fig, String subIndent) {
        LayoutManager layoutManager = fig.getLayoutManager();
        String lm ;
        if (layoutManager == null) {
            lm = "none";
        } else if (layoutManager instanceof ChainedLayout) {
            lm = layoutManager.toString() ;
        } else if (layoutManager.getClass().getEnclosingClass() != null) {
            lm = layoutManager.getClass().getName();
        } else {
            lm = layoutManager.getClass().getSimpleName();
        }
        
        this.out.append(subIndent);
        this.out.append("layout=");
        this.out.append(lm);
        this.out.append("\n");
    }

    @objid ("f0651cba-785e-4e9f-baba-912dff33dd66")
    private static class IsValidatedFigSelector implements Predicate<IFigure> {
        @objid ("faf7f9fc-2400-4fe3-a438-ef04e4370fe6")
        private Method m;

        @objid ("4507f45b-1eb7-4dc2-a20b-e7c3a5bbca36")
        public IsValidatedFigSelector() {
            try {
                this.m = Figure.class.getDeclaredMethod("isValid");
                this.m.setAccessible(true);
                //MethodHandles.lookup().unreflect(m).invoke(args)
            } catch (NoSuchMethodException | SecurityException e) {
                throw new ExceptionInInitializerError(e);
            }
        }

        @objid ("fc8906d3-015e-4f9a-9b28-66731f112cc8")
        @Override
        public boolean test(IFigure t) {
            try {
                return (boolean) this.m.invoke(t);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new LinkageError(e.toString(), e);
            }
        }

    }

}

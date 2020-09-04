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

package org.modelio.diagram.editor.silent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GraphicsSource;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Display;

/**
 * Should be same as {@link org.eclipse.draw2d.DeferredUpdateManager}
 * but does validation immediately.
 */
@objid ("669bbea8-33f7-11e2-95fe-001ec947c8cc")
final class SynchronousUpdateManager extends UpdateManager {
    @objid ("669e20f0-33f7-11e2-95fe-001ec947c8cc")
    private boolean updateQueued;

    @objid ("669e20f1-33f7-11e2-95fe-001ec947c8cc")
    private boolean updating;

    @objid ("669e20f2-33f7-11e2-95fe-001ec947c8cc")
    private boolean validating;

    @objid ("6c7d3afb-0a14-42ed-8b49-ac8def1d3b5e")
    private boolean syncValidationEnabled;

    @objid ("669bbea9-33f7-11e2-95fe-001ec947c8cc")
    private Rectangle damage;

    @objid ("669bbeaa-33f7-11e2-95fe-001ec947c8cc")
    private Map<IFigure, Rectangle> dirtyRegions = new HashMap<>();

    @objid ("669bbeae-33f7-11e2-95fe-001ec947c8cc")
    private GraphicsSource graphicsSource;

    @objid ("669e20ec-33f7-11e2-95fe-001ec947c8cc")
    private List<IFigure> invalidFigures = new ArrayList<>();

    @objid ("669e20ef-33f7-11e2-95fe-001ec947c8cc")
    private IFigure root;

    @objid ("669e20f3-33f7-11e2-95fe-001ec947c8cc")
    private RunnableChain afterUpdate;

    /**
     * Adds a dirty region (defined by the rectangle <i>x, y, w, h</i>) to the update queue. If the figure isn't visible
     * or either the width or height are 0, the method returns without queueing the dirty region.
     * 
     * @param figure the figure that contains the dirty region
     * @param x the x coordinate of the dirty region
     * @param y the y coordinate of the dirty region
     * @param w the width of the dirty region
     * @param h the height of the dirty region
     */
    @objid ("669e20f4-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public synchronized void addDirtyRegion(final IFigure figure, final int x, final int y, final int w, final int h) {
        if (w == 0 || h == 0 || !figure.isShowing()) {
            return;
        }
        
        Rectangle rect = this.dirtyRegions.get(figure);
        if (rect == null) {
            rect = new Rectangle(x, y, w, h);
            this.dirtyRegions.put(figure, rect);
        } else {
            rect.union(x, y, w, h);
        }
        
        queueWork();
    }

    /**
     * Validates immediately the figure and schedule repaint.
     * 
     * @param f the invalid figure
     */
    @objid ("66a08350-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public synchronized void addInvalidFigure(final IFigure f) {
        if (this.invalidFigures.contains(f)) {
            return;
        }
        
        // Watch-dog for validation cycles
        if (this.validating) {
            final int MAX_ATTEMPT = 20;
            int size = this.invalidFigures.size();
            if (size >= MAX_ATTEMPT) {
                // Log attempts
                if (size == MAX_ATTEMPT) {
                    // String dump = new FigureDumper().withOnlyInvalidfigures().dump(f);
                    // DiagramEditor.LOG.error(new IllegalStateException(String.format("Validation cycle detected on %s.\n\t Invalid figures: %s", f, dump)));
        
                    // add a dummy figure to increment size
                    this.invalidFigures.add(new Figure());
                }
                // Ignore call to break cycle
                return;
            }
        }
        
        this.invalidFigures.add(f);
        
        queueWork();
        
        if (this.syncValidationEnabled) {
            performValidation();
        }
    }

    /**
     * Returns a Graphics object for the given region.
     * 
     * @param region the region to be repainted
     * @return the Graphics object
     */
    @objid ("66a08357-33f7-11e2-95fe-001ec947c8cc")
    protected Graphics getGraphics(final Rectangle region) {
        if (this.graphicsSource == null) {
            return null;
        }
        return this.graphicsSource.getGraphics(region);
    }

    /**
     * Performs the update. Validates the invalid figures and then repaints the dirty regions.
     * @see #validateFigures()
     * @see #repairDamage()
     */
    @objid ("66a0835e-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public synchronized void performUpdate() {
        if (isDisposed() || this.updating) {
            return;
        }
        this.updating = true;
        try {
            performValidation();
            this.updateQueued = false;
            repairDamage();
            if (this.afterUpdate != null) {
                RunnableChain chain = this.afterUpdate;
                this.afterUpdate = null;
                chain.run(); // chain may queue additional Runnable.
                if (this.afterUpdate != null) {
                    queueWork();
                }
            }
        } finally {
            this.updating = false;
        }
    }

    /**
     * @see UpdateManager#performValidation()
     */
    @objid ("66a08363-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public synchronized void performValidation() {
        if (this.invalidFigures.isEmpty() || this.validating) {
            return;
        }
        try {
            IFigure fig;
            this.validating = true;
            fireValidating();
            for (int i = 0; i < this.invalidFigures.size(); i++) {
                fig = this.invalidFigures.get(i);
                this.invalidFigures.set(i, null);
                fig.validate();
            }
        } finally {
            this.invalidFigures.clear();
            this.validating = false;
        }
    }

    /**
     * Adds the given exposed region to the update queue and then performs the update.
     * 
     * @param exposed the exposed region
     */
    @objid ("66a08368-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public synchronized void performUpdate(final Rectangle exposed) {
        addDirtyRegion(this.root, exposed);
        performUpdate();
    }

    /**
     * Posts an {@link UpdateRequest} using {@link Display#asyncExec(Runnable)}.
     * <p>
     * If work has already been queued, a new request is not needed.
     */
    @objid ("66a0836f-33f7-11e2-95fe-001ec947c8cc")
    private void queueWork() {
        if (!this.updateQueued) {
            this.updateQueued = true;
            sendUpdateRequest();
        }
    }

    /**
     * Fires the <code>UpdateRequest</code> to the current display asynchronously.
     * @since 3.2
     */
    @objid ("66a08372-33f7-11e2-95fe-001ec947c8cc")
    private void sendUpdateRequest() {
        Display display = Display.getDefault();
        display.asyncExec(new UpdateRequest());
    }

    /**
     * Releases the graphics object, which causes the GraphicsSource to flush.
     * 
     * @param graphics the graphics object
     */
    @objid ("66a08375-33f7-11e2-95fe-001ec947c8cc")
    protected void releaseGraphics(final Graphics graphics) {
        graphics.dispose();
        this.graphicsSource.flushGraphics(this.damage);
    }

    /**
     * Repaints the dirty regions on the update queue and calls {@link UpdateManager#firePainting(Rectangle, Map)},
     * unless there are no dirty regions.
     */
    @objid ("66a0837a-33f7-11e2-95fe-001ec947c8cc")
    protected void repairDamage() {
        Iterator<IFigure> keys = this.dirtyRegions.keySet().iterator();
        Rectangle contribution;
        IFigure figure;
        IFigure walker;
        
        while (keys.hasNext()) {
            figure = keys.next();
            walker = figure.getParent();
            contribution = this.dirtyRegions.get(figure);
            // A figure can't paint beyond its own bounds
            contribution.intersect(figure.getBounds());
            while (!contribution.isEmpty() && walker != null) {
                walker.translateToParent(contribution);
                contribution.intersect(walker.getBounds());
                walker = walker.getParent();
            }
            if (this.damage == null) {
                this.damage = new Rectangle(contribution);
            } else {
                this.damage.union(contribution);
            }
        }
        
        if (!this.dirtyRegions.isEmpty()) {
            Map<IFigure, Rectangle> oldRegions = this.dirtyRegions;
            this.dirtyRegions = new HashMap<>();
            firePainting(this.damage, oldRegions);
        }
        
        if (this.damage != null && !this.damage.isEmpty()) {
            // ystem.out.println(damage);
            Graphics graphics = getGraphics(this.damage);
            if (graphics != null) {
                this.root.paint(graphics);
                releaseGraphics(graphics);
            }
        }
        this.damage = null;
    }

    /**
     * Adds the given runnable and queues an update if an update is not under progress.
     * 
     * @param runnable the runnable
     */
    @objid ("66a0837d-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public synchronized void runWithUpdate(final Runnable runnable) {
        this.afterUpdate = new RunnableChain(runnable, this.afterUpdate);
        if (!this.updating) {
            queueWork();
        }
    }

    /**
     * Sets the graphics source.
     * 
     * @param gs the graphics source
     */
    @objid ("66a08384-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setGraphicsSource(final GraphicsSource gs) {
        this.graphicsSource = gs;
    }

    /**
     * Sets the root figure.
     * 
     * @param figure the root figure
     */
    @objid ("66a0838a-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void setRoot(final IFigure figure) {
        this.root = figure;
    }

    /**
     * Validates all invalid figures on the update queue and calls {@link UpdateManager#fireValidating()} unless there
     * are no invalid figures.
     */
    @objid ("66a08390-33f7-11e2-95fe-001ec947c8cc")
    protected void validateFigures() {
        performValidation();
    }

    @objid ("66a08393-33f7-11e2-95fe-001ec947c8cc")
    public SynchronousUpdateManager() {
        super();
    }

    /**
     * Enable or disable synchronous validation.
     * 
     * @param syncValidationEnabled whether synchronous validation must be enabled.
     */
    @objid ("5e138dd2-d3a7-4f4a-8fe7-ba6c6f312f14")
    public void setSyncValidationEnabled(boolean syncValidationEnabled) {
        this.syncValidationEnabled = syncValidationEnabled;
    }

    /**
     * Calls {@link SynchronousUpdateManager#performUpdate()}.
     */
    @objid ("66a08395-33f7-11e2-95fe-001ec947c8cc")
    class UpdateRequest implements Runnable {
        @objid ("66a08397-33f7-11e2-95fe-001ec947c8cc")
        public UpdateRequest() {
            super();
        }

        /**
         * Calls {@link SynchronousUpdateManager#performUpdate()}.
         */
        @objid ("66a08399-33f7-11e2-95fe-001ec947c8cc")
        @Override
        public void run() {
            performUpdate();
        }

    }

    @objid ("66a2e5a0-33f7-11e2-95fe-001ec947c8cc")
    class RunnableChain {
        @objid ("66a2e5a1-33f7-11e2-95fe-001ec947c8cc")
         RunnableChain next;

        @objid ("66a2e5a2-33f7-11e2-95fe-001ec947c8cc")
         Runnable run;

        @objid ("66a2e5a3-33f7-11e2-95fe-001ec947c8cc")
        RunnableChain(final Runnable run, final RunnableChain next) {
            this.run = run;
            this.next = next;
        }

        @objid ("66a2e5a9-33f7-11e2-95fe-001ec947c8cc")
        void run() {
            if (this.next != null) {
                this.next.run();
            }
            this.run.run();
        }

    }

}

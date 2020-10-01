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

package org.modelio.diagram.elements.common.portcontainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutListener;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.UpdateManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.common.freezone.TranslateChildrenOnResizeEditPolicy;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.commands.NoopCommand;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DelegatingEditPolicy;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Edit part for port container.
 */
@objid ("7eef9176-1dec-11e2-8cad-001ec947c8cc")
public class PortContainerEditPart extends AbstractNodeEditPart {
    @objid ("e0104c2e-4370-4c75-962a-39fb60efdf6f")
    private static final boolean DEBUG = false;

    @objid ("f1312d1a-3870-4e16-8cda-e6e523874d01")
    private boolean dragPolicyInstalled;

    /**
     * Is true on when all parent {@link #activate()} methods have been called
     * so that the edit part is model change listener.
     * <p>
     * Fixes https://mantis.softeam.com/view.php?id=13019
     */
    @objid ("dd96afd5-6a8b-4ccb-8933-c7413a9c9f4e")
    private boolean ready;

    @objid ("7c708df2-86e9-40b0-98ed-d03c2ba40ed8")
    private final List<Command> postRefreshCommands = new ArrayList<>(0);

    @objid ("1135908d-711f-4450-9458-cf82c1eafdff")
    private Rectangle cachedTrimmedBounds;

    /**
     * C'tor.
     */
    @objid ("7eef917b-1dec-11e2-8cad-001ec947c8cc")
    public PortContainerEditPart() {
        super();
        // Add a listener that will provoke self resize on child addition and
        // subtraction.
        addEditPartListener(new EditPartListener.Stub() {
            @Override
            public void childAdded(EditPart child, int index) {
                GmPortContainer pc = (GmPortContainer) getModel();
                if (pc.getMainNode() != child.getModel()) {
                    scheduleAutoResize();
                }
            }
        
            @Override
            public void removingChild(EditPart child, int index) {
                GmPortContainer pc = (GmPortContainer) getModel();
                if (pc.getMainNode() != child.getModel()) {
                    scheduleAutoResize();
                }
            }
        
        });
    }

    @objid ("af389dea-f46c-4eb8-97b6-d14a167108c7")
    @Override
    public void activate() {
        super.activate();
        
        this.ready = true;
        
        // Run post refresh commands if everything is ready.
        runPostRefreshCommands();
    }

    /**
     * @return the {@link GmPortContainer} model.
     */
    @objid ("83260f20-8bba-4700-b1f3-e29503e597d7")
    public GmPortContainer getPortContainerModel() {
        return (GmPortContainer) getModel();
    }

    /**
     * Return one that will keep the bounds of
     * the container correct and instead resize the main node (if any).
     */
    @objid ("7eef917e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public SelectionEditPolicy getPreferredDragRolePolicy(String requestType) {
        return new AutoSizeEditPolicy2();
    }

    @objid ("354dc083-3c1e-4507-b9ab-2c2171c2c5eb")
    @Override
    public void installEditPolicy(Object key, EditPolicy editPolicy) {
        super.installEditPolicy(key, editPolicy);
        
        if (key.equals(EditPolicy.PRIMARY_DRAG_ROLE)) {
            this.dragPolicyInstalled = (editPolicy != null);
        }
        
        // Run post refresh commands if everything is ready.
        runPostRefreshCommands();
    }

    /**
     * Provoke the self resize of the container by sending a RESIZE request (with resize delta being (0,0)) on the
     * container.
     */
    @objid ("bcbc430d-7050-47b8-8fe6-85c2bd65450f")
    void autoResize() {
        // Request a "fake" resize of container, so that it can adapts
        // itself to its new child.
        ChangeBoundsRequest resizeContainerRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        resizeContainerRequest.setEditParts(PortContainerEditPart.this);
        resizeContainerRequest.setSizeDelta(new Dimension(0, 0));
        Command resizeContainerCommand = getCommand(resizeContainerRequest);
        if (resizeContainerCommand != null && resizeContainerCommand.canExecute()) {
        
            if (PortContainerEditPart.DEBUG) {
                if (DiagramElements.LOG.isDebugEnabled()) {
                    Throwable t = new Throwable("Auto resizing " + this);
        
                    DiagramElements.LOG.debug("%s : \n\t%s\n\t%s\n\t%s", t.getMessage(), t.getStackTrace()[0], t.getStackTrace()[1], t.getStackTrace()[2]);
                }
            }
        
            resizeContainerCommand.execute();
        }
    }

    @objid ("7eef9189-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void addChildVisual(EditPart childEditPart, final int index) {
        final PortContainerFigure portContainerFigure = (PortContainerFigure) getFigure();
        final IFigure childFigure = ((GraphicalEditPart) childEditPart).getFigure();
        
        final GmPortContainer pc = (GmPortContainer) getModel();
        final GmNodeModel childModel = (GmNodeModel) childEditPart.getModel();
        final Object childModelLayoutData = childModel.getLayoutData();
        boolean isMainNode = false;
        
        if (childModel == pc.getMainNode()) {
            // Indicate main node figure to the container's figure and layout so
            // it can use it for handle bounds and for laying out the ports.
            isMainNode = true;
            portContainerFigure.setMainNodeFigure(childFigure);
            portContainerFigure.getPortContainerLayout().setMainNodeFigure(childFigure);
        
            if (childModelLayoutData == null) {
                // The main node constraint needs to be initialized first
                this.postRefreshCommands.add(0, new MainNodeInitializeCommand(portContainerFigure, childModel));
            } else if (childModelLayoutData instanceof Rectangle && ((Rectangle) childModelLayoutData).isEmpty()) {
                // The main node constraint needs to be fixed first to its preferred size
                childFigure.setSize(childFigure.getPreferredSize());
                this.postRefreshCommands.add(0, new MainNodeInitializeCommand(childFigure, childModel));
            }
        
        } else if (pc.isSatellite(childModel)) {
            // Override the IDragTrackerProvider of the child EditPart to use a
            // custom one that will handle the fact that a satellite should not
            // be reparented.
            ((AbstractNodeEditPart) childEditPart).setDragTrackerProvider(new SatelliteDragTrackerProvider(childEditPart));
        } else {
            assert (pc.isPort(childModel)) : String.format("Unsupported type of child role '%s' for %s",
                    childModel.getRoleInComposition(), childModel);
        }
        
        // Some child layout data need to be initialized once the figures have been layouted.
        if (childModelLayoutData != null) {
            if (childModelLayoutData instanceof Integer) {
                // Specific case of a satellite placed "ex nihilo": schedule the satellite placement.
                this.postRefreshCommands.add(new ChildSatelliteInitializeCommand(
                        portContainerFigure,
                        childFigure,
                        childModel));
        
            } else if (pc.isPort(childModel)) {
                if (childModelLayoutData instanceof Rectangle) {
                    // Changes the temporary Rectangle constraint to a PortConstraint
                    Rectangle oldConstraint = (Rectangle) childModelLayoutData;
                    Border referenceBorder = portContainerFigure.getPortContainerLayout().determineReferenceBorder(portContainerFigure,
                            oldConstraint);
                    final PortConstraint newConstraint = new PortConstraint();
                    newConstraint.setRequestedBounds(oldConstraint);
                    newConstraint.setReferenceBorder(referenceBorder);
        
                    childModel.setLayoutData(newConstraint);
                } else if (childModelLayoutData instanceof Border) {
                    // Specific case of a port placed "ex nihilo":
                    // Schedule change of Border to a PortConstraint
                    this.postRefreshCommands.add(new ChildPortInitializeCommand(childFigure, childModel));
                }
            }
        }
        
        // Actually add the child.
        // Quite same as calling super.addChildVisual(childEditPart, index)
        // except we do set the constraint right now instead of later.
        if (isMainNode) {
            // Insert main node in first position
            getContentPane().add(childFigure, childModelLayoutData, 0);
        } else {
            // Insert other nodes at requested position
            getContentPane().add(childFigure, childModelLayoutData, index);
        }
    }

    @objid ("7eef9191-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new PortContainerEditPolicy());
        installEditPolicy("Delegation", new DelegatingEditPolicy());
        installEditPolicy("satellite selection", new SatelliteChildrenSelectionPolicy());
        installEditPolicy(TranslateChildrenOnResizeEditPolicy.class, null);
    }

    @objid ("7eef9194-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IFigure createFigure() {
        PortContainerFigure fig = new PortContainerFigure();
        
        // Style independent properties.
        fig.setLayoutManager(new PortContainerLayout());
        
        // Trimmed bound caching management
        /*
         * fig.addLayoutListener(new LayoutListener.Stub() {
         *
         * @SuppressWarnings("synthetic-access")
         *
         * @Override
         * public void invalidate(IFigure container) {
         * PortContainerEditPart.this.cachedTrimmedBounds = null;
         * }
         * });
         */
        fig.addFigureListener(new FigureListener() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void figureMoved(IFigure source) {
                PortContainerEditPart.this.cachedTrimmedBounds = null;
            }
        });
        
        // Style dependent properties.
        refreshFromStyle(fig, getModelStyle());
        
        // Layout debugging
        if (PortContainerEditPart.DEBUG) {
            fig.setBorder(new LineBorder(ColorConstants.red));
        
            RectangleFigure trimmedFig = new RectangleFigure() {
                @Override
                public boolean containsPoint(int x, int y) {
                    // this figure must be invisible for user mouse
                    return false;
                }
            };
            trimmedFig.setAlpha(160);
            trimmedFig.setOpaque(false);
            trimmedFig.setFill(false);
            trimmedFig.setForegroundColor(ColorConstants.blue);
            fig.add(trimmedFig);
        
            fig.addLayoutListener(new LayoutListener.Stub() {
                @Override
                public void postLayout(IFigure container) {
                    if (PortContainerEditPart.this.cachedTrimmedBounds != null) {
                        trimmedFig.setBounds(PortContainerEditPart.this.cachedTrimmedBounds);
                    } else {
                        trimmedFig.setBounds(container.getBounds().getExpanded(-1, -1));
                    }
                }
            });
        
            // debug : log bounds changes
            if (false) {
                PortContainerEditPart thisEp = this;
                fig.addFigureListener(new FigureListener() {
                    private Rectangle oldBounds;
        
                    @Override
                    public void figureMoved(IFigure source) {
                        Rectangle newBounds = source.getBounds();
                        if (this.oldBounds == null) {
                            this.oldBounds = newBounds.getCopy();
                        } else if (!this.oldBounds.equals(newBounds)) {
                            if (this.oldBounds.x() > newBounds.x()) {
                                DiagramElements.LOG.debug("   PortContainerEditPart.FigureListener: %s \n\t\tLEFT moved from %s \n\t\t to %s", thisEp, this.oldBounds, newBounds);
                            } else {
                                DiagramElements.LOG.debug("   PortContainerEditPart.FigureListener: %s \n\t\t moved from %s \n\t\t to %s", thisEp, this.oldBounds, newBounds);
                            }
                            this.oldBounds.setBounds(newBounds);
                        }
                    }
                });
            }
        }
        return fig;
    }

    /**
     * @return the port container figure.
     */
    @objid ("efdadfd6-4090-414c-a63e-cfee3149ffb5")
    protected PortContainerFigure getPortContainerFigure() {
        return (PortContainerFigure) getFigure();
    }

    @objid ("4d22181b-77e7-4377-b9f0-c35a47169be6")
    @Override
    protected void refreshChildren() {
        super.refreshChildren();
        
        runPostRefreshCommands();
    }

    @objid ("7eef919b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void refreshVisuals() {
        IFigure portContainerFigure = getFigure();
        GmPortContainer portContainerModel = (GmPortContainer) getModel();
        
        IFigure parent = portContainerFigure.getParent();
        if (parent != null) {
            parent.setConstraint(portContainerFigure, portContainerModel.getLayoutData());
        }
        
        super.refreshVisuals();
    }

    @objid ("7ef1f3ab-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        // Just watch out for the main node:
        GmPortContainer pc = (GmPortContainer) getModel();
        if (pc.getMainNode() == childEditPart.getModel()) {
            PortContainerFigure pcFig = (PortContainerFigure) getFigure();
            pcFig.setMainNodeFigure(null);
            pcFig.getPortContainerLayout().setMainNodeFigure(null);
        }
        
        // Actually remove the child.
        super.removeChildVisual(childEditPart);
    }

    /**
     * Run all commands added to {@link #postRefreshCommands}.
     * <p>
     * Figures layout is triggered before each command execution.
     * Triggers an {@link #autoResize()} after all commands have been run.
     * <p>
     * Post refresh commands are run only if the edit part is active and has a drag policy.
     */
    @objid ("f7866f43-ca1d-4313-a70e-485f206e951b")
    protected void runPostRefreshCommands() {
        // Run post refresh commands only if the edit part is active and has a drag policy.
        // use this.ready instead of isActivated() that returns true before model change listener is plugged.
        if (this.ready && this.dragPolicyInstalled && !this.postRefreshCommands.isEmpty()) {
            UpdateManager updateManager = getFigure().getUpdateManager();
        
            // Try 3 times to consume commands
            for (int i = 0; i < 3 && !this.postRefreshCommands.isEmpty(); i++) {
                for (Iterator<Command> it = this.postRefreshCommands.iterator(); it.hasNext();) {
                    Command command = it.next();
                    if (command.canExecute()) {
                        updateManager.performValidation();
        
                        command.execute();
                        it.remove();
                    }
                }
            }
        
            // Log not run commands, there should be none here.
            if (PortContainerEditPart.DEBUG && !this.postRefreshCommands.isEmpty()) {
                DiagramElements.LOG.debug("%d post refresh commands not executable:", this.postRefreshCommands.size());
                for (Command command : this.postRefreshCommands) {
                    DiagramElements.LOG.debug("  - %s", command);
                }
            }
        
            updateManager.performValidation();
            autoResize();
        }
    }

    @objid ("e0b075ce-2785-4157-977d-f4feab1d405b")
    protected void scheduleAutoResize() {
        this.postRefreshCommands.add(new NoopCommand());
    }

    @objid ("ad9e7ba1-a8e4-4c22-b201-3a58c6f4f30e")
    @Override
    public Rectangle getTrimmedBounds() {
        if (this.cachedTrimmedBounds == null) {
            this.cachedTrimmedBounds = computeTrimmedBounds(null, null);
        }
        return this.cachedTrimmedBounds;
    }

    @objid ("36ec876d-ab26-4716-9423-0c077ded4994")
    final Rectangle computeTrimmedBounds(EditPart changedChild, Object changedConstraint) {
        PortContainerFigure portContainerFigure = getPortContainerFigure();
        GmPortContainer pcModel = getPortContainerModel();
        
        Rectangle ret = null;
        
        PortContainerLayoutHelper h = new PortContainerLayoutHelper(portContainerFigure);
        Point layoutOrigin = PortContainerLayoutHelper.getLayoutOrigin(portContainerFigure);
        List<GraphicalEditPart> lchildren = getChildren();
        for (GraphicalEditPart child : lchildren) {
            GmNodeModel cm = (GmNodeModel) child.getModel();
            if (!pcModel.isSatellite(cm)) {
                IFigure childFigure = child.getFigure();
                Object childConstraint;
                if (child == changedChild) {
                    childConstraint = changedConstraint;
                } else {
                    childConstraint = portContainerFigure.getLayoutManager().getConstraint(childFigure);
                }
        
                Rectangle r = h.getRectFromConstraint(childConstraint, childFigure).getCopy();
                r.translate(layoutOrigin);
        
                Rectangle oldBounds = childFigure.getBounds().getCopy();
                childFigure.setBounds(r);
                r = ((AbstractNodeEditPart) child).getTrimmedBounds().getCopy();
                childFigure.setBounds(oldBounds);
        
                if (ret == null) {
                    ret = r.getCopy();
                } else {
                    ret.union(r);
                }
            }
        }
        
        if (ret == null) {
            return portContainerFigure.getBounds();
        }
        return ret;
    }

    @objid ("98546df3-ac1c-4e0b-a10b-5b5ee4442711")
    @Override
    public void deactivate() {
        this.ready = false;
        
        super.deactivate();
    }

    /**
     * Sets the port initial bounds if not defined.
     * <p>
     * Computes the initial location of a port based on the bounds of the main node, a placement constraint
     * expressed as a value from {@link PortConstraint.Border} and the preferred size of the satellite.
     * 
     * @author cmarin from previous FPO post layout listener
     * @since 3.4
     */
    @objid ("7a6ff4f1-af57-44fe-b13e-53807a7b983f")
    private class ChildPortInitializeCommand extends Command {
        @objid ("aef36c05-a055-440a-b608-2b692fb2be92")
        private final GmNodeModel childModel;

        @objid ("37c22f94-ed5e-4c03-b790-91bb8716d9bc")
        private final IFigure childFigure;

        /**
         * Converts an Integer (interpreted as a value from {@link PositionConstants} to a Rectangle.
         * 
         * @param childFigure the child figure for which to convert the constraint.
         * @param childModel the child model for which to convert the constraint.
         */
        @objid ("14ef886c-5f6d-4852-b91c-8b8dd48c7662")
        public ChildPortInitializeCommand(final IFigure childFigure, final GmNodeModel childModel) {
            this.childFigure = childFigure;
            this.childModel = childModel;
        }

        @objid ("5da65000-b1f0-405d-a0ac-ece1af0533dc")
        @Override
        public boolean canExecute() {
            Rectangle mainConstraint = getPortContainerFigure().getPortContainerLayout().getMainNodeConstraint();
            return mainConstraint != null;
        }

        @objid ("6868fc14-4c07-4ada-8705-c5dd0a784e80")
        @Override
        public void execute() {
            final PortContainerFigure container = getPortContainerFigure();
            
            // Avoid setting a constraint when the figure isn't attached to 'container'
            if (this.childFigure.getParent() == container) {
                if (this.childModel.getLayoutData() instanceof Border) {
                    final Border portConstraint = (Border) this.childModel.getLayoutData();
                    final PortConstraint newConstraint = computeRectangleFromBorder(container, portConstraint);
            
                    this.childModel.setLayoutData(newConstraint);
                    container.setConstraint(this.childFigure, newConstraint);
                }
            }
        }

        /**
         * Converts a {@link Border} to a Rectangle.
         * 
         * @param container the PortContainerFigure
         * @return the new rectangle constraint.
         */
        @objid ("1cbd2644-c1d7-4ccd-8d9d-389036904859")
        private PortConstraint computeRectangleFromBorder(PortContainerFigure container, final Border placement) {
            // Let's suppose that it is a placement constraint from
            // PositionConstants, and we'll place the child around the main
            // node's figure.
            Rectangle newRect = new Rectangle(0, 0, -1, -1);
            // 1 - Get the bounds of the main node (default to (0, 0, 0, 0) if
            // not found).
            Rectangle mainNodeBounds = container.getPortContainerLayout().getMainNodeConstraint();
            if (mainNodeBounds == null) {
                mainNodeBounds = new Rectangle(0, 0, 0, 0);
            }
            
            // 2 - define a constraint around the main node bounds (default to EAST).
            Dimension childPreferredSize = this.childFigure.getPreferredSize();
            newRect.setLocation(computePortInitialLocation(mainNodeBounds, placement, childPreferredSize));
            
            PortConstraint newConstraint = new PortConstraint();
            newConstraint.setReferenceBorder(placement);
            newConstraint.setRequestedBounds(newRect);
            return newConstraint;
        }

        /**
         * Computes the initial location of a port based on the bounds of the main node, a placement constraint
         * expressed as a value from {@link Border} and the preferred size of the satellite.
         * 
         * @param mainNodeBounds the bounds of the main node.
         * @param placement the border on which the port should be.
         * @param childSize the preferred size of the Port
         * @return the initial location of center of the Port.
         */
        @objid ("49875440-5608-4b23-9d5d-6cc13c46478c")
        private Point computePortInitialLocation(final Rectangle mainNodeBounds, final Border placement, final Dimension childSize) {
            switch (placement) {
            case SouthEast:
                return mainNodeBounds.getBottomRight();
            
            case South:
                return mainNodeBounds.getBottom().translate(-childSize.width / 2, 0);
            
            case SouthWest:
                return mainNodeBounds.getBottomLeft().translate(-childSize.width, 0);
            
            case West:
                return mainNodeBounds.getLeft().translate(-childSize.width, -childSize.height / 2);
            case NorthWest:
                return mainNodeBounds.getTopLeft().translate(-childSize.width, -childSize.height);
            
            case North:
                return mainNodeBounds.getTop().translate(-childSize.width / 2, -childSize.height);
            
            case NorthEast:
                return mainNodeBounds.getTopRight().translate(0, -childSize.height);
            
            case East:
            default:
                return mainNodeBounds.getRight().translate(0, -childSize.height / 2);
            }
        }

    }

    /**
     * Sets the satellite initial bounds.
     * <p>
     * Computes the initial location of a satellite based on the bounds of the main node, a placement constraint
     * expressed as a value from {@link PositionConstants} and the preferred size of the satellite.
     * 
     * @author cmarin from previous FPO post layout listener
     * @since 3.4
     */
    @objid ("2a47ffa9-e259-4ec4-ba5c-3a0c18c988e5")
    private static class ChildSatelliteInitializeCommand extends Command {
        @objid ("58634bcb-3bfc-4fad-90ef-13e470e5473e")
        private final GmNodeModel childModel;

        @objid ("bbce3629-0f3a-4fb8-aaf9-baf68aa089f1")
        private final PortContainerFigure containerFig;

        @objid ("8e718de9-bb01-4868-9ef7-5ce0112f5c18")
        private final IFigure childFigure;

        /**
         * Converts an Integer (interpreted as a value from {@link PositionConstants} to a Rectangle.
         * 
         * @param containerFig the parent port container figure
         * @param childFigure the child figure for which to convert the constraint.
         * @param childModel the child model for which to convert the constraint.
         */
        @objid ("3aa7e9df-00df-472a-b804-d5aefed70b8c")
        public ChildSatelliteInitializeCommand(PortContainerFigure containerFig, final IFigure childFigure, final GmNodeModel childModel) {
            this.containerFig = containerFig;
            this.childFigure = childFigure;
            this.childModel = childModel;
        }

        @objid ("bcb3d206-066d-4ee7-a4dc-19b20e793665")
        @Override
        public boolean canExecute() {
            Rectangle mainConstraint = this.containerFig.getPortContainerLayout().getMainNodeConstraint();
            return mainConstraint != null;
        }

        @objid ("112209a9-d518-40e0-ad92-dbd2b155c2eb")
        @Override
        public void execute() {
            // Avoid setting a constraint when the figure isn't attached to 'content pane'
            if (this.childFigure.getParent() == this.containerFig) {
                Rectangle newConstraint = convertIntConstraintToRectangleConstraint();
            
                this.childModel.setLayoutData(newConstraint);
                this.containerFig.setConstraint(this.childFigure, this.childModel.getLayoutData());
            }
        }

        /**
         * Converts an Integer (interpreted as a value from {@link PositionConstants} to a Rectangle.
         * 
         * @return the new rectangle constraint.
         */
        @objid ("f78f554e-f7cd-4ff4-b193-b7470f7cc907")
        private Rectangle convertIntConstraintToRectangleConstraint() {
            // Let's suppose that it is a placement constraint from
            // PositionConstants, and we'll place the child around the main
            // node's figure.
            Rectangle newConstraint = new Rectangle(0, 0, -1, -1);
            // 1 - Get the bounds of the main node (default to (0, 0, 0, 0) if
            // not found).
            Rectangle mainNodeBounds = this.containerFig.getPortContainerLayout().getMainNodeConstraint();
            if (mainNodeBounds == null) {
                mainNodeBounds = new Rectangle(0, 0, 0, 0);
            }
            
            // 2 - define a constraint around the main node bounds (default to EAST).
            int placement = ((Integer) this.childModel.getLayoutData()).intValue();
            Dimension childPreferredSize = this.childFigure.getPreferredSize();
            newConstraint.setSize(childPreferredSize);
            newConstraint.setLocation(computeSatelliteInitialLocation(mainNodeBounds,
                    placement,
                    childPreferredSize));
            return newConstraint;
        }

        /**
         * Computes the initial location of a satellite based on the bounds of the main node, a placement constraint
         * expressed as a value from {@link PositionConstants} and the preferred size of the satellite.
         * 
         * @param mainNodeBounds the bounds of the main node.
         * @param placement a placement constraint expressed as a value from {@link PositionConstants}. Can be either
         * {@link PositionConstants#SOUTH_EAST}, {@link PositionConstants#SOUTH},
         * {@link PositionConstants#SOUTH_WEST}, {@link PositionConstants#WEST},
         * {@link PositionConstants#NORTH_WEST}, {@link PositionConstants#NORTH},
         * {@link PositionConstants#NORTH_EAST} or {@link PositionConstants#EAST} which is the default.
         * @param childPreferredSize the preferred size of the satellite
         * @return the initial location of the satellite.
         */
        @objid ("b08a3e42-6fa6-4ccf-b243-b9186945c586")
        private Point computeSatelliteInitialLocation(Rectangle mainNodeBounds, int placement, Dimension childPreferredSize) {
            switch (placement) {
            case PositionConstants.SOUTH_EAST:
                return mainNodeBounds.getBottomRight();
            
            case PositionConstants.SOUTH:
                return mainNodeBounds.getBottom().translate(-childPreferredSize.width / 2, 0);
            
            case PositionConstants.SOUTH_WEST:
                return mainNodeBounds.getBottomLeft().translate(-childPreferredSize.width, 0);
            
            case PositionConstants.WEST:
                return mainNodeBounds.getLeft().translate(-childPreferredSize.width,
                        -childPreferredSize.height / 2);
            case PositionConstants.NORTH_WEST:
                return mainNodeBounds.getTopLeft().translate(-childPreferredSize.width,
                        -childPreferredSize.height);
            
            case PositionConstants.NORTH:
                return mainNodeBounds.getTop().translate(-childPreferredSize.width / 2,
                        -childPreferredSize.height);
            
            case PositionConstants.NORTH_EAST:
                return mainNodeBounds.getTopRight().translate(0, -childPreferredSize.height);
            
            case PositionConstants.EAST:
            default:
                return mainNodeBounds.getRight().translate(0, -childPreferredSize.height / 2);
            }
        }

    }

    /**
     * Initialize the main node constraint when not already set.
     * <p>
     * This command should be run before other initialization commands,
     * that need the main node constraint to be set.
     * 
     * @author cmarin
     * @since 3.4
     */
    @objid ("1e3b095a-60b2-435d-986f-072c0d2bcd21")
    private static class MainNodeInitializeCommand extends Command {
        @objid ("68f71680-0e1b-44f5-ae9f-881ac0df8aa5")
        private final GmNodeModel childModel;

        @objid ("cbcdf220-5fd9-4b87-acdc-fb17c4d79373")
        private final IFigure refFigure;

        /**
         * @param refFigure The figure whose size will be used
         * @param childModel The model whose initialize layout data will be initialized.
         */
        @objid ("24cd5d57-62d9-4ad5-8707-d7fd15edd702")
        public MainNodeInitializeCommand(final IFigure refFigure, final GmNodeModel childModel) {
            this.refFigure = refFigure;
            this.childModel = childModel;
        }

        @objid ("35c5dbef-6420-4cb4-add9-b02146f44ef9")
        @Override
        public void execute() {
            Object layoutData = this.childModel.getLayoutData();
            if (layoutData == null) {
                final Rectangle newConstraint = new Rectangle().setSize(this.refFigure.getSize());
                this.childModel.setLayoutData(newConstraint);
            } else {
                Rectangle r = (Rectangle) layoutData;
                if (r.width() < 0 || r.height() < 0) {
                    final Rectangle newConstraint = new Rectangle().setSize(this.refFigure.getSize());
                    this.childModel.setLayoutData(newConstraint);
                }
            }
        }

    }

}

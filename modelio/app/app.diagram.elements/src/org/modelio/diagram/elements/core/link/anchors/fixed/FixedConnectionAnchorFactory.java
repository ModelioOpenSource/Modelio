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
package org.modelio.diagram.elements.core.link.anchors.fixed;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.elements.core.figures.ShapedFigure;
import org.modelio.diagram.elements.core.figures.anchors.FacesConstants;
import org.modelio.diagram.elements.core.figures.anchors.FixedAnchor;
import org.modelio.diagram.elements.core.figures.anchors.IFixedAnchorLocator;
import org.modelio.diagram.elements.core.link.anchors.GmFixedAnchor;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;

/**
 * Node anchor provider for nodes that have fixed anchors.
 * <p>
 * This class may be redefined.
 * In this case sub classes should redefine:<ul>
 * <li> the {@link ComputedState} class to store their own computed data.
 * <li> {@link FixedConnectionAnchorFactory#createComputedState(IFigure)} to instantiate the right sub class.
 * </ul>
 */
@objid ("2e85df70-bb94-43f3-b89e-44f1ef453c2f")
@Deprecated
class FixedConnectionAnchorFactory implements IFixedConnectionAnchorFactory {
    @objid ("50ba9276-f985-4a7d-8b55-508d9c3cafb4")
    private final String algorithmId;

    @objid ("2fa8cf7a-173f-4134-80d1-2aacab0f0ef5")
    private final Dimension initAnchorCount;

    @objid ("1d68ff7c-0205-4825-b34c-6eb8086daa26")
    private ComputedState computedState;

    @objid ("65aeca72-b9ad-4356-80f7-3069065761b1")
    private static final Rectangle TMP_BOUNDS = new Rectangle();

    @objid ("95997c9d-59bc-49f6-b52e-ef7f22eb48a8")
    private IFixedAnchorLocator commonLocator;

    @objid ("75a6a01a-14a1-4e64-9324-d71b5186e020")
    private final Map<IFigure, SoftReference<IFixedAnchorLocator>> locators = new WeakHashMap<>();

    /**
     * Constructor for nodes that have a constant number of anchors per face.
     * @param nHorizontal number of anchors on horizontal faces
     * @param nVertical number of anchors on vertical faces
     */
    @objid ("6653078c-972b-4e71-99da-466af6024bce")
    public  FixedConnectionAnchorFactory(String algorithmId, int nHorizontal, int nVertical) {
        this.algorithmId = algorithmId;
        this.initAnchorCount= new Dimension(nHorizontal, nVertical);
        
    }

    /**
     * Constructor for sub classes that have computed anchor count.
     * <p>
     * Use {@link #fixed(int, int)} for nodes that have a fixed anchor count.
     */
    @objid ("63d946cc-8595-4149-ada7-e3d565f0ff8f")
    protected  FixedConnectionAnchorFactory(String algorithmId) {
        this.algorithmId = algorithmId;
        this.initAnchorCount = null;
        
    }

    @objid ("733f0294-57ed-40d0-89e0-5c48da70eed2")
    @Override
    public String getAlgorithmId() {
        return this.algorithmId;
    }

    /**
     * <p>Create a draw2d anchor from a model anchor</p>
     * @param aNodeFigure the node figure the anchor is anchored to
     * @param gmAnchor the anchor model
     * @return the draw2d anchor
     */
    @objid ("0e3c1f6e-2179-4b95-ba0b-857e8d9a2c77")
    @Override
    public ConnectionAnchor createFromModel(IFigure aNodeFigure, GmFixedAnchor gmAnchor) {
        return new FixedAnchor(
                aNodeFigure,
                gmAnchor.getFace(),
                gmAnchor.getRank(),
                gmAnchor.getTotalOnFace(),
                getLocator(aNodeFigure));
        
    }

    /**
     * Get the up to date computed state for the given node figure.
     * <p>
     * Calls {@link #createComputedState(IFigure)} and/or {@link ComputedState#update(IFigure)} if needed.
     * @param aNodeFigure a node figure
     * @return the up to date computations
     */
    @objid ("0e130cbb-617d-432f-8259-3b28fd4fbe33")
    protected final ComputedState getState(IFigure aNodeFigure) {
        if (this.computedState==null ) {
            this.computedState = createComputedState(aNodeFigure);
            if (this.initAnchorCount != null) {
                this.computedState.anchorsCount.setSize(this.initAnchorCount);
            }
        }
        
        this.computedState.setLocator(getLocator(aNodeFigure));
        this.computedState.validate(aNodeFigure);
        return this.computedState;
    }

    /**
     * Create a {@link ComputedState} for a node figure.
     * <p>
     * Must be redefined by sub classes that extends {@link ComputedState} .
     * @param aNodeFigure the node figure for which a state is needed.
     */
    @objid ("6affe61a-1ffa-4705-87dd-79a40bc96dea")
    protected ComputedState createComputedState(IFigure aNodeFigure) {
        return new ComputedState();
    }

    /**
     * Return all possible anchors for the node and the connection routing mode.
     * @param aNodeFigure a node figure
     * @param routerId the connection routing mode
     * @return all possible anchors
     */
    @objid ("a4f071cd-8c6b-443e-baf1-ce8fff677fd5")
    @Override
    public Collection<ConnectionAnchor> getAllAnchors(IFigure aNodeFigure, ConnectionRouterId routerId, Integer face) {
        return getState(aNodeFigure).getAllAnchors(face);
    }

    /**
     * Called by connection anchors and manually when the node figure changes.
     * <p>
     * Invalidates computed state.
     */
    @objid ("790ad2cd-45c4-45a4-8708-16b4ffe59f22")
    protected void onFigureMoved(IFigure figure) {
        if (this.computedState != null) {
            this.computedState.invalidate();
        }
        
    }

    /**
     * Get a {@link IFixedAnchorLocator} for the given node figure.
     * <p>
     * Return this by default. May be redefined by sub classes.
     * @param aNodeFigure a node figure.
     */
    @objid ("7c40b5cf-0e42-4b5a-b25c-f1ddadc7663c")
    protected IFixedAnchorLocator getLocator(IFigure aNodeFigure) {
        if (this.commonLocator == null) {
            this.commonLocator = createCommonLocator( aNodeFigure);
        }
        
        if (false && aNodeFigure instanceof ShapedFigure) {
            // Experimental anchors for  ShapedFigure
            return this.locators.compute(aNodeFigure, (k, v) ->
            v != null && v.get() != null ? v :
            new SoftReference<>(new ShapedFigureAnchorLocator(this.commonLocator))).get();
        } else {
            return this.commonLocator;
        }
        
    }

    /**
     * Create the common locator.
     * @param aNodeFigure a figure in case of it is needed.
     * @return the common locator
     */
    @objid ("f723d279-9e5d-4f7f-a79a-36dfff3f48e3")
    private final IFixedAnchorLocator createCommonLocator(IFigure aNodeFigure) {
        IFixedAnchorLocator leafLoc = createAnchorLocator(getAlgorithmId(), aNodeFigure);
        return decorateLocator( leafLoc);
    }

    /**
     * Embed (or not) the given locator into another that adjust its beahvior.
     * @param leafLoc a locator
     * @return the same or another.
     */
    @objid ("9f6135ab-5818-4bb5-a23b-c306d8deca6e")
    protected IFixedAnchorLocator decorateLocator(IFixedAnchorLocator leafLoc) {
        return new TolerantFixedAnchorLocator(leafLoc, 1);
    }

    /**
     * Create the real {@link IFixedAnchorLocator} for this factory.
     * @param aNodeFigure a figure in case of it is needed.
     * @return the anchor locator.
     */
    @objid ("c362477e-0217-4bed-b2c1-31267ce54c64")
    protected IFixedAnchorLocator createAnchorLocator(String algoId, IFigure aNodeFigure) {
        return new FixedNodeAnchorLocator(algoId, this::onFigureMoved);
    }

    /**
     * State class that holds data computed from the node figure.
     * <p>
     * Instances are created by {@link FixedConnectionAnchorFactory#createComputedState(IFigure)}.
     * <p>
     * {@link FixedConnectionAnchorFactory#getState(IFigure)} calls {@link #update(IFigure)} to update computations.
     * <p>
     * This class may be redefined.
     * In this case sub classes should redefine:<ul>
     * <li> {@link #update(IFigure)}
     * <li> {@link FixedConnectionAnchorFactory#createComputedState(IFigure)} to instantiate the right sub class.
     * </ul>
     */
    @objid ("cbf601ae-74e6-4d05-8e24-a6c27e7c9d6d")
    protected static class ComputedState {
        @objid ("61cfd79a-83ac-4598-8746-14051ed4fa9e")
        private boolean isUpToDate;

        @objid ("8c2530ef-7597-46e6-bd13-c1a4002125e1")
        private int lastFace = -1;

        @objid ("83013da0-4845-4c82-9f0c-c7407cabafe2")
        protected Collection<ConnectionAnchor> anchors = new ArrayList<>();

        @objid ("920f4be3-a39f-43b2-b674-3bb25710865f")
        protected Dimension anchorsCount = new Dimension();

        @objid ("5368271d-79ad-48af-bc9b-9214bf2a6618")
        private IFixedAnchorLocator locator;

        @objid ("69fd1449-8e30-4341-a929-92e605ed146a")
        private final Dimension previousAnchorCount = new Dimension();

        @objid ("4ccac2d0-9e61-4250-a69d-b3da1cfc010e")
        private IFigure previousFigure;

        @objid ("2b853491-6387-48e7-9864-e8e31d97a42b")
        protected Collection<ConnectionAnchor> faceAnchors = new ArrayList<>();

        /**
         * Update computed properties from the node figure.
         * <p>
         * The node figure could be different from the original one if the edit part has been deactivated then activated again.
         * @param newNodeFigure the new node figure.
         * @return true if the state is now valid, false if {@link #update(IFigure)} should be called again next time.
         */
        @objid ("c5ccc433-e342-4cb3-b664-da3206e2a448")
        protected boolean update(IFigure newNodeFigure) {
            int newLen = 2 * (this.anchorsCount.width + this.anchorsCount.height);
            
            if (! this.anchors.isEmpty() ) {
                ConnectionAnchor a = this.anchors.iterator().next();
                if (a.getOwner() == newNodeFigure) {
                    // array will be same, fast exit
                    return true;
                }
            }
            
            this.anchors = new ArrayList<>(newLen);
            this.faceAnchors = null;
            
            if (newLen > 0) {
                IFixedAnchorLocator algo = getLocator(newNodeFigure);
            
                for (int i = 0; i < this.anchorsCount.width; i++) {
                    this.anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_NORTH, i, this.anchorsCount.width, algo));
                    this.anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_SOUTH, i, this.anchorsCount.width, algo));
                }
            
                for (int i = 0; i < this.anchorsCount.height; i++) {
                    this.anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_EAST, i, this.anchorsCount.height, algo));
                    this.anchors.add(new FixedAnchor(newNodeFigure, FacesConstants.FACE_WEST, i, this.anchorsCount.height, algo));
                }
            }
            
            this.previousAnchorCount.setSize(this.anchorsCount);
            return true;
        }

        @objid ("56505033-5efe-446d-b399-b11b5c4a168b")
        public Collection<ConnectionAnchor> getAllAnchors(Integer face) {
            if (face == null)
                return this.anchors;
            
            if (face.intValue() == this.lastFace && this.faceAnchors != null)
                return this.faceAnchors;
            
            this.faceAnchors = new ArrayList<>();
            for (ConnectionAnchor a : this.anchors) {
                if (((FixedAnchor) a).getFace() == face.intValue())
                    this.faceAnchors.add(a);
            }
            return this.faceAnchors;
        }

        /**
         * Ensure this instance computations are up to date.
         * <p>
         * Calls {@link #update(IFigure)} if needed.
         * @param aNodeFigure a node figure.
         */
        @objid ("0f3b739e-fc99-4a8d-877f-59543175b577")
        public final void validate(IFigure aNodeFigure) {
            if (isUpToDate(aNodeFigure))
                return;
            
            this.isUpToDate = update(aNodeFigure);
            this.previousFigure = aNodeFigure;
            
        }

        /**
         * Get the anchor locator for the given figure.
         * <p>
         * Might be redefined by sub classes that wish to use different locator depending on anything.
         * @param aNodeFigure a node figure
         */
        @objid ("bb12eeac-6dd5-4083-b440-318b5a3ff04e")
        protected IFixedAnchorLocator getLocator(IFigure aNodeFigure) {
            return this.locator;
        }

        /**
         * Called by {@link FixedConnectionAnchorFactory#getState(IFigure)} to set the {@link IFixedAnchorLocator} as itself.
         * @param locator the anchor locator to use.
         */
        @objid ("a94b0622-ba62-4be5-8e6b-5e43962d993c")
        public void setLocator(IFixedAnchorLocator locator) {
            this.locator = locator;
        }

        /**
         * Mark the ComputedState as out of date.
         * <p>
         * Next call to {@link #update(IFigure)} will remake computations.
         */
        @objid ("20626052-b2a8-4310-96a0-46dbce55f531")
        public final void invalidate() {
            this.isUpToDate = false;
            this.previousFigure = null;
            
        }

        @objid ("190b4f3a-9750-4e23-95bc-2ddc1956bc11")
        public final boolean isUpToDate(IFigure aNodeFigure) {
            return this.isUpToDate && this.previousFigure==aNodeFigure && checkIsUpToDate(aNodeFigure);
        }

        @objid ("b1e46287-b27e-4415-b3ac-022a451482e8")
        protected boolean checkIsUpToDate(IFigure aNodeFigure) {
            return true;
        }

    }

}

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
package org.modelio.diagram.elements.core.figures.routers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.modelio.diagram.elements.core.link.MPoint;
import org.modelio.diagram.elements.core.link.path.BendPointUtils;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * Data object that stores safely a {@link Connection} source anchor, target anchor and constraint.
 */
@objid ("c482e8c3-ab0b-4ba4-bbfd-2f699024fb13")
public class ConnectionState {
    @objid ("9d85ec04-44db-43b5-b231-93d1a077e4a8")
    private boolean mutable = true;

    @objid ("6444ae46-ddda-43cb-a443-8e242b8f8eb5")
    private Object constraint;

    @objid ("9dfb68da-9b85-4119-b5b3-c0410fe81341")
    private ConnectionAnchor targetAnchor;

    @objid ("bba9fa02-fcff-4b22-a3bf-bde3e4982307")
    private ConnectionAnchor sourceAnchor;

    /**
     * A shared instance for short computations.
     */
    @objid ("d0eebeba-cd17-4924-96e0-95fefe28536f")
    public static final ConnectionState SHARED = new ConnectionState();

    /**
     * Initialize this state from the given connection.
     * <p>
     * The constraint is copied deeply : no point instance is shared.
     * @param connection the connection to initialize from
     * @return this instance
     */
    @objid ("6367f273-ae05-45c1-8d80-cfd1a8365331")
    public ConnectionState init(Connection connection) {
        checkMutable();
        setSourceAnchor(connection.getSourceAnchor());
        setTargetAnchor(connection.getTargetAnchor());
        
        Object routingConstraint = connection.getRoutingConstraint();
        if (routingConstraint==null) {
            setConstraint(new ArrayList<>(0));
        } else if (BendPointUtils.isMPointList(routingConstraint)){
            setConstraint(BendPointUtils.copyConstraint((List<MPoint>) routingConstraint));
        } else {
            setConstraint(routingConstraint);
        }
        return this;
    }

    @objid ("ec0d64a1-e9de-46ba-ad64-76889aa6f73c")
    public ConnectionState immutable() {
        this.mutable = false;
        return this;
    }

    @objid ("f4cf42d8-b602-4a66-9907-c496293d5e4c")
    private void checkMutable() throws IllegalStateException {
        if (! this.mutable)
            throw new IllegalStateException(String.format("Immutable state: %s", this));
        
    }

    /**
     * Copy constructor : initialize this instance from another one.
     * <p>
     * The constraint is copied deeply : no point instance is shared.
     * @param other the state to copy
     * @return this instance.
     */
    @objid ("c97441bf-32b0-47ed-9c59-afbb05b40124")
    public ConnectionState init(ConnectionState other) {
        checkMutable();
        setSourceAnchor(other.getSourceAnchor());
        setTargetAnchor(other.getTargetAnchor());
        
        List<MPoint> routingConstraint = other.getMPoints();
        if (routingConstraint==null) {
            setConstraint(new ArrayList<>(0));
        } else if (BendPointUtils.isMPointList(routingConstraint)){
            setConstraint(BendPointUtils.copyConstraint(routingConstraint));
        } else {
            DiagramElements.LOG.debug(new UnsupportedOperationException(String.format("Clone not supported for %s", routingConstraint)));
            setConstraint(routingConstraint);
        }
        return this;
    }

    /**
     * Apply this state to the given connection.
     * <p>
     * The constraint is copied deeply : no point instance is shared.
     * @param connection the connection to reroute.
     */
    @objid ("b8404890-97ed-4063-92db-8b0e45644593")
    public void applyTo(Connection connection) {
        if (!Objects.equals(connection.getSourceAnchor(), this.sourceAnchor))
            connection.setSourceAnchor(this.sourceAnchor);
        
        if (!Objects.equals(connection.getTargetAnchor(), this.targetAnchor))
            connection.setTargetAnchor(this.targetAnchor);
        
        Object routingConstraint = getConstraint();
        if (routingConstraint == null ) {
            connection.setRoutingConstraint(null);
        } else if (BendPointUtils.isMPointList(routingConstraint )){
            connection.setRoutingConstraint(BendPointUtils.copyConstraint(getMPoints()));
        } else {
            // don't know how to clone, apply as is
            connection.setRoutingConstraint(routingConstraint);
        }
        
    }

    /**
     * Copy this constraint into the given one.
     * <p>
     * The target state is overwritten.
     * @param other the state to rewrite.
     */
    @objid ("30e438b7-4137-4bfe-be02-7d09fa36f45b")
    public void applyTo(ConnectionState other) {
        other.init(this);
    }

    /**
     * @return the contained routing constraint
     */
    @objid ("899ede58-a1ce-460d-8c88-f6413d81241b")
    public Object getConstraint() {
        return this.constraint;
    }

    /**
     * @return the source {@link ConnectionAnchor}
     */
    @objid ("de67bed3-6ce5-46f3-88cc-02174f198c6d")
    public ConnectionAnchor getSourceAnchor() {
        return this.sourceAnchor;
    }

    /**
     * @return the target {@link ConnectionAnchor}
     */
    @objid ("a77232f8-9346-4098-81d2-b678cfca8bbd")
    public ConnectionAnchor getTargetAnchor() {
        return this.targetAnchor;
    }

    /**
     * @param constraint the new routing constraint
     * @return this instance
     */
    @objid ("de436484-1e7b-4f0e-9e0c-e51396de5bed")
    public ConnectionState setConstraint(Object constraint) {
        checkMutable();
        this.constraint = constraint;
        return this;
    }

    /**
     * @param sourceAnchor the source {@link ConnectionAnchor}
     * @return this instance
     */
    @objid ("dd1690cd-b241-47da-84cc-fe60b8a7c092")
    public ConnectionState setSourceAnchor(ConnectionAnchor sourceAnchor) {
        checkMutable();
        this.sourceAnchor = sourceAnchor;
        return this;
    }

    /**
     * @param targetAnchor the target {@link ConnectionAnchor}
     * @return this instance
     */
    @objid ("cf215b28-0c81-4a9b-aebf-ca4a84dd5f6a")
    public ConnectionState setTargetAnchor(ConnectionAnchor targetAnchor) {
        checkMutable();
        this.targetAnchor = targetAnchor;
        return this;
    }

    /**
     * @see #getConstraint()
     * @return {@link #getConstraint()} casted to a list of MPoints.
     */
    @objid ("89bd8656-da52-4293-aeb1-02d4b6f9d97e")
    @SuppressWarnings ("unchecked")
    public List<MPoint> getMPoints() {
        return (List<MPoint>) getConstraint();
    }

    @objid ("b71b2978-8b12-46cf-bb0f-bc9a581a8882")
    @Override
    public String toString() {
        return String.format(
                "%s [%ssourceAnchor=%s, targetAnchor=%s, constraint=%s]",
                this.mutable ? "" : "IMMUTABLE ",
                getClass().getSimpleName(),
                this.sourceAnchor,
                this.targetAnchor,
                this.constraint);
        
    }

    @objid ("41117f70-9d56-4b4b-9267-67edf1d99e72")
    @Override
    public int hashCode() {
        return Objects.hash(this.mutable, this.constraint, this.sourceAnchor, this.targetAnchor);
    }

    @objid ("74f11ac4-37fc-434a-a0cf-82cf503fe507")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConnectionState other = (ConnectionState) obj;
        if (other.mutable != this.mutable)
            return false;
        return isSame(other);
    }

    /**
     * Tells whether the given state has same anchors and constraint as this state.
     * @param other a connectionState
     * @return whether the passed state has same anchors and constraint as this state.
     */
    @objid ("4be217ad-173c-4d2b-81b1-2f891103062e")
    public boolean isSame(ConnectionState other) {
        return Objects.equals(this.constraint, other.constraint)
                && Objects.equals(this.sourceAnchor, other.sourceAnchor)
                && Objects.equals(this.targetAnchor, other.targetAnchor);
        
    }

}

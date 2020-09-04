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

package org.modelio.vcore.smkernel;

import java.io.Serializable;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.SmClass;

/**
 * Interface for model object data classes.
 */
@objid ("003ff822-702c-1f21-85a5-001ec947cd2a")
public interface ISmObjectData extends Serializable, IAccessOrdered {
    /**
     * @return the universal identifier.
     */
    @objid ("0040009c-702c-1f21-85a5-001ec947cd2a")
    String getUuid();

    /**
     * @return the metaclass.
     */
    @objid ("00400e0c-702c-1f21-85a5-001ec947cd2a")
    SmClass getClassOf();

    /**
     * @return the metaobject.
     */
    @objid ("004049f8-702c-1f21-85a5-001ec947cd2a")
    IMetaOf getMetaOf();

    /**
     * @param newMetaObject the metaobject.
     */
    @objid ("0040531c-702c-1f21-85a5-001ec947cd2a")
    void setMetaOf(final IMetaOf newMetaObject);

    /**
     * @return the object status
     */
    @objid ("00405e70-702c-1f21-85a5-001ec947cd2a")
    long getStatus();

    /**
     * Initialize the data
     * @param uuid the object universal identifier
     * @param liveId the live identifier
     */
    @objid ("0081b42e-eb1b-1f22-8c06-001ec947cd2a")
    void init(final String uuid, final long liveId);

    /**
     * @return the repository object.
     */
    @objid ("00821194-eb1b-1f22-8c06-001ec947cd2a")
    IRepositoryObject getRepositoryObject();

    /**
     * Set the repository object.
     * @param createObject the repository object.
     */
    @objid ("00827972-eb1b-1f22-8c06-001ec947cd2a")
    void setRepositoryObject(final IRepositoryObject createObject);

    /**
     * @return the live identifier.
     */
    @objid ("001f06a8-fd1a-1f27-a7da-001ec947cd2a")
    long getLiveId();

    /**
     * Set the given flags state.
     * <p>
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * @param flags a combination of flags. Use the constants defined in {@link IRStatus}.
     * @param state the flags state
     */
    @objid ("af086f85-d65c-49ad-905e-9bc419d0abfa")
    void setRFlags(long flags, StatusState state);

    /**
     * Set the given flags state.
     * <p>
     * Use the constants defined in {@link IRStatus}.
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * @param trueFlags a combination of flags to set.
     * @param falseFlags a combination of flags to unset.
     * @param undefFlags a combination of flags to undefine.
     */
    @objid ("53fd1eb8-8b19-41d6-8df6-2d74481f8ebc")
    void setRFlags(long trueFlags, long falseFlags, long undefFlags);

    /**
     * Set the given persistent flags state.
     * <p>
     * Use the constants defined in {@link IPStatus}.
     * No delete flag must be undefined, in the other case Modelio behavior is undefined.
     * @param trueFlags a combination of flags to set.
     * @param falseFlags a combination of flags to unset.
     * @param undefFlags a combination of flags to undefine.
     */
    @objid ("f85354b1-3f9f-4cf8-a928-fe2fd5e663be")
    void setPFlags(long trueFlags, long falseFlags, long undefFlags);

    /**
     * Set the given persistent flags state.
     * @param flags a combination of flags. Use the constants defined in {@link IRStatus}.
     * @param state the flags state*
     */
    @objid ("cbaebcac-0303-48eb-84c4-f84747c0f116")
    void setPFlags(long flags, StatusState state);

    /**
     * Tells whether all the given flags are set in this object status. Returns:
     * <ul>
     * <li> <code>StatusState.UNDEFINED</code> if any of the flags is not defined,
     * <li> <code>StatusState.TRUE</code> if all of them are defined and set to <code>StatusState.TRUE</code>,
     * <li> <code>StatusState.FALSE</code> if all of them are defined and some are set to <code>StatusState.FALSE</code>.
     * </ul>
     * @param flags the flags to test
     * @return <code>StatusState.UNDEFINED</code> if any of the flags is not defined, <code>StatusState.TRUE</code> if all of them are defined
     * and set, else <code>StatusState.FALSE</code>.
     */
    @objid ("bfaafc6a-63f6-41a9-b4f2-fb170b2b0144")
    StatusState hasAllStatus(long flags);

    /**
     * Tells whether any of the given status flags is set to TRUE.
     * <ul>
     * <li> <code>StatusState.TRUE</code> if any of them are defined and set to <code>StatusState.TRUE</code>,
     * <li> <code>StatusState.UNDEFINED</code> if no flag is TRUE and at least one flag is UNDEFINED,
     * <li> <code>StatusState.FALSE</code> if all of them are defined to <code>StatusState.FALSE</code>.
     * </ul>
     * @param flags the flags to test
     * @return the test result.
     */
    @objid ("1e7fb059-238f-449b-a2e6-6af37c11bc7e")
    StatusState hasAnyStatus(long flags);

}

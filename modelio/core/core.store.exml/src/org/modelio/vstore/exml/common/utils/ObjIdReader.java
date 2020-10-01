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

package org.modelio.vstore.exml.common.utils;

import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vstore.exml.common.model.ObjId;
import org.modelio.vstore.exml.common.model.ObjIdName;

/**
 * {@link ObjId} factory from strings with missing metaclasses logging.
 * <p>
 * Creates a fake metaclass and log it when a metaclass is not in the metamodel.
 * 
 * @author cmarin
 * @since 3.5.1
 */
@objid ("c24fb95c-3ce0-4fa1-8b05-a2453ee75259")
public class ObjIdReader {
    @objid ("56453df6-f793-4192-a6f1-87ba32671223")
    private final SmMetamodel metamodel;

    @objid ("3669c773-cf3e-4a9e-83df-b36a8435f8f0")
    private final Supplier<String> logPrefixer;

    @objid ("48a1f3e8-9487-4434-a672-40bbbd54f8ac")
    private final Supplier<String> logSuffixer;

    /**
     * @param metamodel the known metamodel
     * @param logPrefixer a log prefix provider used when fake metaclasses are created.
     * @param logSuffixer a log suffix provider used when fake metaclasses are created.
     */
    @objid ("d97f2f57-5101-4fd1-8154-aaf0eb7b692e")
    public ObjIdReader(SmMetamodel metamodel, Supplier<String> logPrefixer, Supplier<String> logSuffixer) {
        this.metamodel = metamodel;
        this.logPrefixer = logPrefixer;
        this.logSuffixer = logSuffixer;
    }

    @objid ("d7c8dde6-a6e8-4723-b531-56f4a3768ae1")
    public ObjIdName readObjIdName(final String className, final String uid, final String name, boolean isCmsNode) {
        SmClass mClass = readMetaclass(className, isCmsNode);
        return new ObjIdName(mClass, name, uid);
    }

    @objid ("f63cc9a1-5eee-4a82-8a47-acf34a50a77c")
    public ObjIdName readObjIdName(MRef r, boolean isCmsNode) {
        return readObjIdName(r.mc, r.uuid, r.name, isCmsNode);
    }

    @objid ("2631178f-8741-4b7c-b7c9-394ef61476b4")
    public ObjId readObjId(final String className, final String uid, boolean isCmsNode) {
        SmClass mClass = readMetaclass(className, isCmsNode);
        return new ObjId(mClass, uid);
    }

    @objid ("507b6015-f07e-4eb0-9fa2-5563adc2602c")
    private SmClass readMetaclass(final String className, boolean isCmsNode) {
        SmClass mClass = this.metamodel.getMClass(className);
        if (mClass == null) {
            // Assert the metaclass name is given
            if (className == null || className.isEmpty()) {
                throw new IllegalArgumentException(className);
            }
            
            // Create fake metaclass
            mClass = this.metamodel.fakeClassBuilder().setQualifiedName(className).setCmsNode(isCmsNode).build();
        
            // Log metaclass not found
            String message = String.format("%sCreating '%s' fake metaclass%s.", this.logPrefixer.get(), className, this.logSuffixer.get());
            
            Log.warning(message);
            //Log.trace(new Throwable(message));
        }
        return mClass;
    }

    @objid ("b4c12715-390d-4eca-b40a-57f37020cf5c")
    public ObjId readObjId(MRef r, boolean isCmsNode) {
        return readObjId(r.mc, r.uuid, isCmsNode);
    }

}

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

package org.modelio.vstore.jdbm.impl;

import java.io.Closeable;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Utility functions.
 */
@objid ("8aef923b-bbcf-494d-bfa6-03feb6effdbc")
public class Helper {
    @objid ("7f018b67-5496-4f34-8af8-5d944c33a911")
    private Helper() {
        // no instance
    }

    /**
     * Tells whether the given model dependency is stored in the repository.
     * @param dep a model dependency.
     * @return <code>true</code> if stored else <code>false</code>.
     */
    @objid ("10d8e0b1-356f-456f-afd9-4cdbe8dc819d")
    public static boolean isPersistent(SmDependency dep) {
        if (dep == null) {
            return false;
        }
        
        boolean ret = !dep.isTransient() && 
                (dep.isComponent() || 
                        dep.isSharedComposition() || 
                        dep.isPartOf() );
        return ret;
    }

    @objid ("3147454d-4397-4f6c-9f35-0237d5508401")
    public static Closeable toCloseable(final RecordManager db) {
        return new Closeable() {
                
                            @Override
                            public void close() throws IOException {
                                db.close();
                            }
                            
                        };
    }

    @objid ("ed2fc2e4-6f62-42f0-8116-cd3cfd94ef58")
    public static CloseOnFail toCloseOnFail(final RecordManager db) {
        return new CloseOnFail(toCloseable(db));
    }

}

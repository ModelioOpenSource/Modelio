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

package org.modelio.vstore.jdbm7.impl;

import java.io.Closeable;
import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jdbm.RecordManager;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * Utility functions.
 */
@objid ("f029c775-b6e7-4145-b0b7-66cf6a47e44d")
public class Helper {
    @objid ("1a029106-390e-485e-b6eb-d1a2cd3fb57f")
    private Helper() {
        // no instance
    }

    /**
     * Tells whether the given model dependency is stored in the repository.
     * @param dep a model dependency.
     * @return <code>true</code> if stored else <code>false</code>.
     */
    @objid ("f6669d8c-6cdb-487d-9dca-4e6f1c3e0373")
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

    @objid ("6623e613-d41c-4e30-9cdf-64d6ce48c5ab")
    public static Closeable toCloseable(final RecordManager db) {
        return new Closeable() {
                
                            @Override
                            public void close() throws IOException {
                                db.close();
                            }
                            
                        };
    }

    @objid ("75768375-803a-44ba-8061-022f9d53fdf5")
    public static CloseOnFail toCloseOnFail(final RecordManager db) {
        return new CloseOnFail(toCloseable(db));
    }

}

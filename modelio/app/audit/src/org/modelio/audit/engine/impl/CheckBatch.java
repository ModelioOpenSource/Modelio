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

package org.modelio.audit.engine.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IControl;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("06bcc50d-3c76-429f-bfce-ddb77c659b21")
public class CheckBatch {
    @objid ("dfc3a16d-4bde-46c0-8ba6-d9d98d37f09c")
    private String jobId;

    @objid ("b0e2bbac-6f63-4246-95a8-a61b9cd8f455")
    protected MObject element;

    @objid ("bcaea72a-6a05-409b-8773-258599fe6aed")
    protected Map<Integer, IControl> controls = new HashMap<>();

    @objid ("741c4974-9cf8-4fd9-8013-eb4a6c3a443b")
    public MObject getElement() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.element;
    }

    @objid ("935f03b7-3524-491c-9d75-807e6d79ca74")
    public CheckBatch(MObject element, String jobId) {
        this.element = element;
        this.jobId = jobId;
    }

    @objid ("f2057dd8-859c-4a67-994f-edba56158d90")
    public void add(IControl controlToAdd) {
        this.controls.put(controlToAdd.hashId(), controlToAdd);
    }

    @objid ("f31d3957-7a31-4bef-8848-ceea6dfa44cf")
    public int size() {
        return this.controls.size();
    }

    @objid ("20749a3e-65a4-40dc-9aa6-899d6a3ed1bc")
    public Collection<IControl> getControls() {
        return this.controls.values();
    }

    @objid ("49eea3e2-47b7-44cc-84c5-5ab8a2f0cb77")
    public String getJobId() {
        return this.jobId;
    }

}

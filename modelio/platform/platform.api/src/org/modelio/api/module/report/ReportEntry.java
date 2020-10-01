/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.module.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Data model for an entry in a {@link Report}.
 * @since 4.1
 */
@objid ("4cd977bb-d65d-4fae-8ae6-0ad5db09a6bd")
public class ReportEntry implements Comparable<ReportEntry> {
    @mdl.prop
    @objid ("c8697d71-6369-40eb-9836-b64868dcb978")
    private final String helpUrl;

    @mdl.propgetter
    public String getHelpUrl() {
        // Automatically generated method. Please do not modify this code.
        return this.helpUrl;
    }

    @mdl.prop
    @objid ("02ca2386-bf07-4145-98c1-60613263abe4")
    private final String message;

    @mdl.propgetter
    public String getMessage() {
        // Automatically generated method. Please do not modify this code.
        return this.message;
    }

    @mdl.prop
    @objid ("7dc99828-4a20-4bf1-9264-9902733f4b49")
    private final EntryKind kind;

    @mdl.propgetter
    public EntryKind getKind() {
        // Automatically generated method. Please do not modify this code.
        return this.kind;
    }

    @mdl.prop
    @objid ("24d57205-ad83-47c0-8ef8-a4b424b7dfe0")
    private final String category;

    @mdl.propgetter
    public String getCategory() {
        // Automatically generated method. Please do not modify this code.
        return this.category;
    }

    @mdl.prop
    @objid ("8ad7015b-0ae6-4270-a071-9b6f579b0646")
    private final int code;

    @mdl.propgetter
    public int getCode() {
        // Automatically generated method. Please do not modify this code.
        return this.code;
    }

    @mdl.prop
    @objid ("f6ab7d67-7e41-4e6d-9118-09c751ea9795")
    private List<MObject> linkedObjects;

    @mdl.propgetter
    public List<MObject> getLinkedObjects() {
        // Automatically generated method. Please do not modify this code.
        return this.linkedObjects;
    }

    @objid ("18bf5524-f563-4357-a8d7-edac79eb31af")
    @Override
    public int compareTo(ReportEntry o) {
        return this.message.compareTo(o.message);
    }

    @objid ("241c9cf9-f0d1-4a18-b241-d776332d47e1")
    public ReportEntry(EntryKind kind, int code, String category, String helpUrl, String message, MObject[] linkedObjects) {
        this.code = code;
        this.kind = kind;
        this.category = category;
        this.message = message;
        this.helpUrl = helpUrl;
        
        if (linkedObjects != null) {
            List<MObject> values = Arrays.asList(linkedObjects);
            this.linkedObjects = new ArrayList<>(values);
        } else {
            this.linkedObjects = Collections.EMPTY_LIST;
        }
    }

    @objid ("8259bf0e-7559-447a-ae61-61740778958a")
    public boolean isError() {
        return this.kind == EntryKind.ERROR;
    }

    @objid ("cd8663e8-0b99-4d71-9f8e-1b17b8738988")
    public boolean isWarning() {
        return this.kind == EntryKind.WARNING;
    }

    @objid ("26cecba7-e740-4c9f-bc6a-de6ab9a55f5e")
    public boolean isTip() {
        return this.kind == EntryKind.TIP;
    }

    @objid ("b55c7c29-7998-4bbb-9258-977c16db0f1a")
    public boolean isInfo() {
        return this.kind == EntryKind.INFO;
    }

}

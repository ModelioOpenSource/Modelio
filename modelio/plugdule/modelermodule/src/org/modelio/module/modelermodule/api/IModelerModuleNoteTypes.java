/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.module.modelermodule.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("00145b14-3050-4e25-ba95-ee882530b18f")
public interface IModelerModuleNoteTypes {
    @objid ("7ab35d2f-2f51-4ea3-a5bf-e47d252bc688")
    public static final String MODELELEMENT_COMMENT = "comment";

    @objid ("d2cee801-208a-4f29-956d-ad66423ab827")
    public static final String MODELELEMENT_DESCRIPTION = "description";

    @objid ("e71e681b-4edb-42f5-919b-29eb9cf3af90")
    public static final String MODELELEMENT_UNDEFINED = "undefined";

    @objid ("da090af9-f82c-4be3-a39e-fc526612262d")
    public static final String MODELTREE_SUMMARY = "summary";

    @objid ("e7679be7-0a00-466d-9513-0eed756ccdac")
    public static final String USECASE_CONSTRAINT = "constraint";

    @objid ("477c11c1-7031-45fc-b967-306a91ca153d")
    public static final String USECASE_EXCEPTION = "exception";

    @objid ("f943a1da-6a53-4d20-8218-4495b435831f")
    public static final String USECASE_NON_FUNCTIONAL_CONSTRAINT = "non-functional constraint";

    @objid ("75754279-97a2-4eb3-8f56-be2b7912b6a5")
    public static final String USECASE_POSTCONDITION = "postcondition";

    @objid ("51d90841-d71e-4155-8215-ea8a7eed9106")
    public static final String USECASE_PRECONDITION = "precondition";

}

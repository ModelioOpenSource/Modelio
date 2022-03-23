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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Kind of entry reports.
 * @since 4.1
 */
@objid ("73fa4c65-e657-45f2-9094-7449cde0ab3f")
public enum EntryKind {
    @objid ("02fa3df5-fa24-44dd-b091-285f41fe7fce")
    ERROR,
    @objid ("1d89bb79-ced0-457f-b701-4c1631841d8b")
    WARNING,
    @objid ("4bf0279f-bdf0-4e36-8329-30ae7746f318")
    TIP,
    @objid ("d735a54a-1bf4-48ca-8ce3-c16cf41995e9")
    INFO;

}

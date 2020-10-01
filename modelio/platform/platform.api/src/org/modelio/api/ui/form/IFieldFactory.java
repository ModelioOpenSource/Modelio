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

package org.modelio.api.ui.form;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.ui.form.fields.IField;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

/**
 * {@link IField Field} and {@link FormFieldPage pages} factory used by {@link ElementFormPanel}.
 * <p>
 * Don't implement this interface directly, subclass {@link AbstractFieldFactory} instead.
 */
@objid ("eb3c8a0a-7598-4b50-8d6d-12bd501e77a0")
public interface IFieldFactory {
    /**
     * Create form field pages from a specific {@link ModelElement}.
     * 
     * @param input the element to build the pages for.
     */
    @objid ("0e6d1232-b61f-46f5-b8da-a806fdcc0620")
    List<FormFieldPage> createFieldPages(ModelElement input);

    /**
     * Fill a form field page from a specific {@link ModelElement}.
     * 
     * @param parent a widget which will be the parent of the new field instance (cannot be null)
     * @param input the element to build the form field for.
     */
    @objid ("eb3034e6-9480-4cd9-b637-79ef6a7b8ffd")
    List<IField> createFields(FormToolkit toolkit, Composite parent, ModelElement input, FormFieldPage page);

}

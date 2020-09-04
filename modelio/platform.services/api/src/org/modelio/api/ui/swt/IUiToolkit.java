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

package org.modelio.api.ui.swt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.swt.widgets.Composite;
import org.modelio.api.ui.swt.metaclassselect.IMetaclassSelector;

/**
 * Toolkit that creates advanced SWT components.
 * 
 * @since 3.7.1
 */
@objid ("63705246-649f-4ef7-8723-bec327921f10")
public interface IUiToolkit {
    /**
     * Create a metaclass selector widget.
     * @see IMetaclassSelector
     * 
     * @param parent the parent SWT composite.
     * @param style the SWT styles. See {@link org.eclipse.swt.widgets.Text} for available flags.
     * @return the created widget component.
     */
    @objid ("6ae573c1-50fe-4ad1-9d2b-af04493b6a43")
    IMetaclassSelector createMetaclassSelector(Composite parent, int style);

    /**
     * Get a default strategy responsible to determine if a cell selection event triggers an editor activation.
     * <p/>
     * In this implementation, edition can be triggered:
     * <ul>
     * <li>Programmatically</li>
     * <li>By pressing F2</li>
     * <li>If withTimeDelta is true, with two clicks on the same object in a duration comprised between 0.3 and 1 second</li>
     * <li>If withTimeDelta is false, with two clicks on the same object</li>
     * </ul>
     * 
     * @param viewer the ColumnViewer to hook
     * @param withTimeDelta whether to constraint time delta between two mouse click on the same cell
     * @return
     */
    @objid ("13788c00-806c-4030-84bc-ea80d5b5cfeb")
    ColumnViewerEditorActivationStrategy createDefaultEditionStrategy(ColumnViewer viewer, boolean withTimeDelta);

}

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
package org.modelio.api.ui.swt.metaclassselect;

import java.util.function.Consumer;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Text;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * <code>IMetaclassSelector</code> is a reusable component wrapping an SWT Text widget that can
 * be used to select a single metaclass.<br/>
 * The proposal menu is popped whenever one of the following conditions is
 * fulfilled:
 * <ul>
 * <li>the user enters any upper case letter character (as metaclass names
 * always starts with a Capital letter)</li>
 * <li>the user enters CTRL+SPACE which is the standard completion activation
 * sequence</li>
 * </ul>
 * The metaclasses proposed for completion are those defined in the metamodel
 * filtered by {@link #setMetaclassFilter(Predicate)} if such a filter is set.
 * <p>
 * Note: the {@link #getControl()} method is available to reach the
 * inner Text field, typically for layout purposes.
 */
@objid ("0ad43e5c-26c8-4391-ba51-b95ab96c5534")
public interface IMetaclassSelector {
    /**
     * Setup a metaclass filter.
     * @param filter a {@link Predicate} that returns true to display the metaclass, false to exclude it.
     */
    @objid ("08b6aeaa-6b31-4da2-b6fe-35c1d46fd945")
    void setMetaclassFilter(Predicate<MClass> filter);

    /**
     * Add a listener to be notified when the selection changes.
     * @param listener a {@link Consumer} that accepts the selected metaclass.
     */
    @objid ("d38389eb-f663-40cb-b0c4-c2bbf9b7badd")
    void addListener(Consumer<MClass> listener);

    /**
     * Returns the internal text control. Should be used only for setting layout
     * data.
     */
    @objid ("7f9a4772-b988-40c3-8677-6ebf2adbbf90")
    Text getControl();

    /**
     * @return the selected metaclass.
     */
    @objid ("c6db0c17-033c-4557-8820-4c7c5a14d7db")
    MClass getSelected();

    /**
     * remove a listener added with {@link #addListener(Consumer)}.
     * @param listener the listener to remove.
     */
    @objid ("f43dfb13-0362-42e4-b8a1-92d12512961a")
    void removeListener(Consumer<MClass> listener);

    /**
     * Set the selected metaclass.
     * @param mClass the selected metaclass.
     */
    @objid ("ef91aa87-211c-4415-a3b4-beddd7de9a24")
    void setSelected(MClass mClass);
}


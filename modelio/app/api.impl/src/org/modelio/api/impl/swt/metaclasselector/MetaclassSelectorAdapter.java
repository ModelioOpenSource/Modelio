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

package org.modelio.api.impl.swt.metaclasselector;

import java.util.function.Consumer;
import java.util.function.Predicate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.ui.swt.metaclassselect.IMetaclassSelector;
import org.modelio.core.ui.swt.selectmetaclass.IMetaclassSelectorListener;
import org.modelio.core.ui.swt.selectmetaclass.MetaclassSelector;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;

@objid ("3aebf935-005b-4e4f-b30e-3c0bd84a7871")
public class MetaclassSelectorAdapter implements IMetaclassSelector {
    @objid ("d8442323-47fd-4f35-9d90-795bd747c6b0")
    private final MetaclassSelector widget;

    @objid ("6a149b33-a073-4693-a68e-549893a2c997")
    public MetaclassSelectorAdapter(Composite parent, int style, MMetamodel metamodel) {
        this.widget = new MetaclassSelector(parent, style, metamodel);
    }

    @objid ("cf4b2976-d5e7-4780-a3c9-1c0e9b4a4036")
    @Override
    public void setMetaclassFilter(Predicate<MClass> filter) {
        this.widget.setMetaclassFilter(mc -> filter.test(mc));
    }

    @objid ("ffeabec7-afdf-4948-96bd-7ea52883c627")
    @Override
    public synchronized void addListener(final Consumer<MClass> listener) {
        this.widget.addListener(new ListenerAdapter(listener));
    }

    /**
     * Returns the internal text control. Should be used only for setting layout
     * data.
     */
    @objid ("2e625e99-63d3-4e69-8833-c0ae6ac4a478")
    @Override
    public Text getControl() {
        return this.widget.getControl();
    }

    @objid ("42e7be07-025d-42c6-8e5b-f168c4310d70")
    @Override
    public MClass getSelected() {
        return this.widget.getSelected();
    }

    @objid ("1b860d4a-b0f3-4283-8ac9-656c06a05453")
    @Override
    public synchronized void removeListener(final Consumer<MClass> listener) {
        this.widget.removeListener(new ListenerAdapter(listener));
    }

    @objid ("10a61e6a-9e86-4470-9ce4-1b613e930a2f")
    @Override
    public void setSelected(MClass mClass) {
        this.widget.setSelected(mClass);
    }

    @objid ("8c49f3cc-a16e-4c0e-a0d1-5ae770dbfb1b")
    private static final class ListenerAdapter implements IMetaclassSelectorListener {
        @objid ("904a3288-53c3-4ab3-ab62-9ef3af3ffec5")
        public final Consumer<MClass> listener;

        @objid ("a474360c-f891-4ba5-989d-68da24905d0d")
        public ListenerAdapter(Consumer<MClass> listener) {
            this.listener = listener;
        }

        @objid ("60771756-b2c3-4f48-a78e-0483b81e00fb")
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((this.listener == null) ? 0 : this.listener.hashCode());
            return result;
        }

        @objid ("a3407bd8-21da-428a-bbbf-7dffa50c816e")
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ListenerAdapter other = (ListenerAdapter) obj;
            if (this.listener == null) {
                if (other.listener != null) {
                    return false;
                }
            } else if (!this.listener.equals(other.listener)) {
                return false;
            }
            return true;
        }

        @objid ("9de343ec-6b02-45b3-a88f-6da9e5416be7")
        @Override
        public void selectMetaclass(MClass mClass) {
            this.listener.accept(mClass);
        }

    }

}

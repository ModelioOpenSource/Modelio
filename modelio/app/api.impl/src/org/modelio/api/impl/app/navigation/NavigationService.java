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

package org.modelio.api.impl.app.navigation;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.modelio.api.modelio.navigation.INavigationListener;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("3a20c126-5633-4a8a-9d40-24d865b70f5b")
@Creatable
public class NavigationService implements INavigationService {
    @objid ("bdb519b4-e607-4b57-94b7-b9cff613b321")
    @Inject
    private IModelioNavigationService selectionService;

    @objid ("59d4487f-bbc7-4be2-9add-d804e3f9005f")
     List<INavigationListener> listeners = null;

    @objid ("b032da61-2ab5-43a5-ad65-6232b5547fcc")
    public NavigationService() {
        this.listeners = new ArrayList<>();
    }

    @objid ("e390f894-3148-4638-8e2c-3c69a5acdaa3")
    @Override
    public void fireNavigate(MObject target) {
        if (this.selectionService != null) {
            this.selectionService.fireNavigate(target);
        }
    }

    @objid ("49fc37d9-2a14-48e2-8143-af2dcd374d47")
    @Override
    public void fireNavigate(List<MObject> targets) {
        if (this.selectionService != null) {
            this.selectionService.fireNavigate(targets);
        }
    }

    @objid ("7b31167b-ea47-4494-a915-c30b299d80f5")
    @Inject
    @Optional
    public void onNavigateElement(@EventTopic(ModelioEventTopics.NAVIGATE_ELEMENT) final List<MObject> elements) {
        for (INavigationListener listener : this.listeners) {
            listener.navigateTo(elements);
        }
    }

    @objid ("14c8f9ac-9495-42b1-815d-c6985c719773")
    @Inject
    @Optional
    public void onNavigateElement(@EventTopic(ModelioEventTopics.NAVIGATE_ELEMENT) final MObject element) {
        for (INavigationListener listener : this.listeners) {
            listener.navigateTo(element);
        }
    }

    @objid ("656944fc-8d72-4efa-9ecf-adbedd29f39a")
    @Override
    public void addNavigationListener(INavigationListener listener) {
        this.listeners.add(listener);
    }

    @objid ("2ba53622-70d9-48f6-a4a7-60575cc08cfd")
    @Override
    public void removeNavigationListener(INavigationListener listener) {
        this.listeners.remove(listener);
    }

}

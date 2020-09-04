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

package org.modelio.api.module.command;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This enum indicates where a module command is to be contributed.
 */
@objid ("01f41998-0000-0001-0000-000000000000")
public enum ActionLocation {
    /**
     * the application menu
     */
    menu,
    /**
     * the application toolbar
     */
    toolbar,
    /**
     * the property view toolbar
     */
    property,
    /**
     * the explorers and diagram contextual popup menus
     */
    contextualpopup,
    /**
     * the diagram toolbar
     */
    diagramtoolbar;

    @objid ("e00e7656-edf0-11e1-82c2-001ec947ccaf")
    public static ActionLocation getValue(String location) {
        if(menu.toString().equals(location)){
            return menu;
        }else if(toolbar.toString().equals(location)){
            return toolbar;
        } else if(property.toString().equals(location)){
            return property;
        }else if(contextualpopup.toString().equals(location)){
            return contextualpopup;
        }else if(diagramtoolbar.toString().equals(location)){
            return diagramtoolbar;
        }
        return null;
    }

}

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

package org.modelio.linkeditor.handlers.print;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f5082e5a-204a-4d26-9b44-6ee769d06816")
public class PrintPageNavigation {
    /**
     * Position of page on horizontal coordinates
     */
    @objid ("dae2f9cf-247c-4761-9228-60eaa55e0099")
    public int x = 1;

    /**
     * Position of page on vertical coordinates
     */
    @objid ("e2f67806-6e57-4b56-97d4-61591ce92e67")
    public int y = 1;

    @objid ("91bc5e20-416d-4fd9-84a0-f81c9dbd4313")
    private int nbPagesX = 1;

    @objid ("cc537f31-1c80-4b24-a05c-06b6498e66c7")
    private int nbPagesY = 1;

    /**
     * @return
     * Number of pages on horizontal coordinates
     */
    @objid ("19fa923d-fc4a-4953-a144-b40b64ee130a")
    public int getNbPagesX() {
        return this.nbPagesX;
    }

    @objid ("ccb8bd81-72f1-4392-8633-4cbcbf00eff5")
    public void setNbPagesX(int nbPagesX) {
        this.nbPagesX = nbPagesX;
    }

    /**
     * @return
     * Number of pages on vertical coordinates
     */
    @objid ("f1a9c77c-cdd4-47f0-a3bb-9a8a15466296")
    public int getNbPagesY() {
        return this.nbPagesY;
    }

    @objid ("da75cae6-b211-47e6-aa57-d7dd1e3fdb7c")
    public void setNbPagesY(int nbPagesY) {
        this.nbPagesY = nbPagesY;
    }

    @objid ("016b98c7-3b72-4f51-99d1-e63ac1c7ebe9")
    public PrintPageNavigation(int nbPagesX, int nbPagesY) {
        this.nbPagesX = nbPagesX;
        this.nbPagesY = nbPagesY;
    }

    @objid ("d6e89cf5-9ae7-442a-bfe3-44ad879d20af")
    public boolean isLastPage() {
        if (this.x == this.nbPagesX && this.y == this.nbPagesY)
            return true;
        else
            return false;
    }

    @objid ("a29ffd45-9f86-432f-9c2e-6fbd4ed4e84f")
    public boolean isFirstPage() {
        if (this.x == 1 && this.y == 1)
            return true;
        else
            return false;
    }

    /**
     * Browse to the next page
     */
    @objid ("e779e636-ade5-4c5f-89eb-db659cd1c324")
    public void goNextPage() {
        if (isLastPage())
            return;
        else {
            if (this.x < this.nbPagesX)
                this.x = this.x + 1;
            else if (this.x == this.nbPagesX) {
                this.x = 1;
                if (this.y < this.nbPagesY)
                    this.y = this.y + 1;
            }
        }
    }

    /**
     * Browse to the previous page
     */
    @objid ("97768a0e-52f2-4473-847d-ad7871c1cdd2")
    public void goPreviousPage() {
        if (isFirstPage())
            return;
        else {
            if (this.x > 1)
                this.x = this.x - 1;
            else if (this.x == 1) {             
                if (this.y > 1) {
                    this.y = this.y - 1;
                    this.x = this.nbPagesX;
                }
            }
        }
    }

    @objid ("93ee19d5-9f5e-4877-a04b-d89e78adaf21")
    public int getCurrentPageNumber() {
        int currentPageNumber = (this.y - 1)*this.nbPagesX + this.x;
        return currentPageNumber;
    }

    @objid ("077e1a5b-714c-4328-9634-691553e9a919")
    public int getTotalPages() {
        return (this.nbPagesX * this.nbPagesY);
    }

}

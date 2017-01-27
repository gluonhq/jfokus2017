/**
 * Copyright (c) 2016, Gluon Software
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse
 *    or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gluonhq.otn.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.otn.OTNApplication;
import com.gluonhq.otn.OTNView;
import com.gluonhq.otn.model.Exhibitor;
import com.gluonhq.otn.model.Service;
import com.gluonhq.otn.util.OTNBundle;
import com.gluonhq.otn.views.cell.ExhibitorCell;
import com.gluonhq.otn.views.helper.Placeholder;
import javafx.fxml.FXML;

import javax.inject.Inject;

public class ExhibitorsPresenter extends GluonPresenter<OTNApplication> {
    private static final String PLACEHOLDER_MESSAGE = OTNBundle.getString("OTN.EXHIBITORS.PLACEHOLDER_MESSAGE");

    @FXML
    private View exhibitors;
    @FXML
    private CharmListView<Exhibitor, String> lvExhibitors;

    @Inject
    private Service service;

    public void initialize() {
        exhibitors.setOnShowing( event -> {
            AppBar appBar = getApp().getAppBar();
            appBar.setNavIcon(getApp().getNavMenuButton());
            appBar.setTitleText(OTNView.EXHIBITORS.getTitle());
            appBar.getActionItems().add(getApp().getSearchButton());
            lvExhibitors.setSelectedItem(null);
        });

        lvExhibitors.setPlaceholder(new Placeholder(PLACEHOLDER_MESSAGE, OTNView.EXHIBITORS.getMenuIcon()));
        lvExhibitors.setItems(service.retrieveExhibitors());
        lvExhibitors.setCellFactory(lv -> new ExhibitorCell());
//        lvExhibitors.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                OTNView.EXHIBITOR.switchView().ifPresent(presenter ->
//                        ((ExhibitorPresenter) presenter).setExhibitor(newValue));
//            }
//        });
    }
}

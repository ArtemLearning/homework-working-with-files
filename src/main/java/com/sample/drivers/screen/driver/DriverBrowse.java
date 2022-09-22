package com.sample.drivers.screen.driver;

import io.jmix.ui.UiComponents;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.LinkButton;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;

@UiController("Driver.browse")
@UiDescriptor("driver-browse.xml")
@LookupComponent("driversTable")
public class DriverBrowse extends StandardLookup<Driver> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Install(to = "driversTable.attachment", subject = "columnGenerator")
    private Component driversTableAttachmentColumnGenerator(Driver driver) {
        if (driver.getAttachment() != null) {
            LinkButton linkButton = uiComponents.create(LinkButton.class);
            linkButton.setCaption(driver.getAttachment().getFileName());
            linkButton.setAction(new BaseAction("download").withHandler(actionPerformedEvent ->
                    downloader.download(driver.getAttachment())));
            return linkButton;
        }
        return null;
    }
}
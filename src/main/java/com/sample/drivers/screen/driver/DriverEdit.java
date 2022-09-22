package com.sample.drivers.screen.driver;

import io.jmix.ui.component.BrowserFrame;
import io.jmix.ui.component.FileResource;
import io.jmix.ui.component.FileStorageResource;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.sample.drivers.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Driver.edit")
@UiDescriptor("driver-edit.xml")
@EditedEntityContainer("driverDc")
public class DriverEdit extends StandardEditor<Driver> {
    @Autowired
    private BrowserFrame attachmentBrowserFrame;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        refreshAttachmentPreview();
    }

    @Subscribe(id = "driverDc", target = Target.DATA_CONTAINER)
    public void onDriverDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Driver> event) {
        if ("attachment".equals(event.getProperty())) {
            refreshAttachmentPreview();
        }
    }
    
    
    private void refreshAttachmentPreview() {
        Driver driver = getEditedEntity();
        if (driver.getAttachment() != null) {
            attachmentBrowserFrame.setSource(FileStorageResource.class)
                    .setFileReference(driver.getAttachment())
                    .setMimeType(driver.getAttachment().getContentType());

        }

    }
}
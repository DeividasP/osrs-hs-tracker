package com.github.deividasp.hstracker.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

/**
 * @author Deividas Popelskis <deividas.popelskis@gmail.com>
 */
public class FileUtils {

	public static Optional<File> getResourceFile(String filePath) throws URISyntaxException {
		Optional<URL> optionalURL = Optional.ofNullable(FileUtils.class.getClassLoader().getResource(filePath));

		if (!optionalURL.isPresent()) {
			return Optional.empty();
		}

		return Optional.of(new File(optionalURL.get().toURI()));
	}

}
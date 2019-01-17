package pl.parser.nbp.domain;

import java.util.HashSet;
import java.util.Set;

public class CatalogList {
    private Set<String> catalogs = new HashSet<>();

    public CatalogList(Set<String> catalogs) {
        this.catalogs = catalogs;
    }

    public Set<String> getCatalogs() {
        return catalogs;
    }
}

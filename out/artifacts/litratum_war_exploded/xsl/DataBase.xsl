<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>

    <!--<xsl:template match="host">-->
        <!--<db>-->
            <!--<xsl:attribute name="host">-->
                <!--<xsl:value-of select="."/>-->
            <!--</xsl:attribute>-->
        <!--</db>-->
    <!--</xsl:template>-->
    <!--<xsl:template match="user">-->
        <!--<a>-->
            <!--<xsl:attribute name="href">-->
                <!--<xsl:value-of select="@src"/>-->
            <!--</xsl:attribute>-->
        <!--</a>-->
    <!--</xsl:template>-->

</xsl:stylesheet>
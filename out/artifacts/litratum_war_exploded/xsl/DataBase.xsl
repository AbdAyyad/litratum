<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:text>&#xa;</xsl:text>
        <xsl:for-each select="DataBase">
            <xsl:value-of select="host"/><xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="port"/><xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="password"/><xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="name"/><xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="user"/><xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
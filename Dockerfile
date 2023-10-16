FROM openjdk:21
EXPOSE 8080
ENV WORK_DIR="/opt/finance-app/"

COPY --chown=10001:root entrypoint.sh /

RUN mkdir -p $WORK_DIR && chmod ug+rw $WORK_DIR
COPY --chown=10001:root target/financeApp-1.0.jar $WORK_DIR/financeApp.jar
WORKDIR $WORK_DIR
USER 10001
ENTRYPOINT ["/entrypoint.sh"]
